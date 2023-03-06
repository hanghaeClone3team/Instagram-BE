package instagram.instagrambe.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String email;
    private String username;

    public KakaoUserInfoDto(Long id, String email, String username){
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
