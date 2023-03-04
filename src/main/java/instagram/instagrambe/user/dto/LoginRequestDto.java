package instagram.instagrambe.user.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;
    private String email;
}
