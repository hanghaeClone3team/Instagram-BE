package instagram.instagrambe.user.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {

    @Pattern(regexp = "[a-z0-9._]{4,15}")
    private String username;
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @Pattern(regexp = "[!@#$%^&*a-zA-Z0-9]{8,15}",message = "대소문자와 숫자만 가능합니다")
    private String password;

    private String password2;

    private boolean admin = false;

    private String adminToken = "";
}


