package instagram.instagrambe.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import instagram.instagrambe.jwt.JwtUtil;
import instagram.instagrambe.kakao.service.KaKaoService;
import instagram.instagrambe.user.dto.CheckIdDto;
import instagram.instagrambe.user.dto.LoginRequestDto;
import instagram.instagrambe.user.dto.SignupRequestDto;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.user.service.UserService;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KaKaoService kaKaoService;

    @PostMapping("/checkId")
    public ResponseEntity<String> checkId(@Valid @RequestBody CheckIdDto checkIdDto){
        userService.checkId(checkIdDto);
        return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 아이디입니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult result){
        String username = requestDto.getUsername();

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
        }

        if(result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
        if(!passwordEncoder.matches(user.getPassword(), password)) {
            throw new CustomException(ErrorCode.INVALIDATION_PASSWORD);
        }

        User user1 = userRepository.findByUsername(email).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
        userService.login(user1.getUsername(), user1.getRole(), response);

        userService.login(user.getUsername(), user.getRole(), response);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료");
    }

    @GetMapping("/kakao/login")
    public String kaKaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException{
        String createToken = kaKaoService.kaKaoLogin(code, response);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");

        return "redirect:/api/post";
    }
}
