package instagram.instagrambe.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import instagram.instagrambe.user.dto.SearchDto;
import instagram.instagrambe.jwt.JwtUtil;
import instagram.instagrambe.user.dto.CheckIdDto;
import instagram.instagrambe.user.dto.LoginRequestDto;
import instagram.instagrambe.user.dto.SignupRequestDto;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.user.service.KakaoService;
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
import java.util.List;
import java.util.Optional;

@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    @PostMapping("/checkid")
    public ResponseEntity<String> checkId(@Valid @RequestBody CheckIdDto checkIdDto){
        userService.checkId(checkIdDto);
        return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 아이디입니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult result){
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();

        Optional<User> foundUser = userRepository.findByUsername(username);
        if(foundUser.isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
        }

        Optional<User> foundEmail = userRepository.findByEmail(email);
        if(foundEmail.isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
        }

        if(!requestDto.getPassword().equals(requestDto.getPassword2())) {
            throw new CustomException(ErrorCode.INVALIDATION_PASSWORD);
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        if(result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());
        userService.signup(requestDto, username, nickname, email, password, requestDto.getAdminToken());
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        String usernameOrEmail = requestDto.getEmail();
        String password = requestDto.getPassword();

        Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if(user.isEmpty()) throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        userService.login(usernameOrEmail, user.get().getRole(), response);

        if(!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new CustomException(ErrorCode.INVALIDATION_PASSWORD);
        }

        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }

    @GetMapping("/kakao/login")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String createToken = kakaoService.kakaoLogin(code, response);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        // 프론트 서버와 연결 필수
        return "redirect:http://localhost:3000/board";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료");
    }
}