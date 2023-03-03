package instagram.instagrambe.user.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/checkId")
    public ResponseEntity<String> checkId(@Valid @RequestBody CheckIdDto checkIdDto){
        userService.checkId(checkIdDto);
        return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 아이디입니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult result){
        if(result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        userService.login(requestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }
}
