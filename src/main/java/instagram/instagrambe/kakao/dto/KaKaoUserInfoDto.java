package instagram.instagrambe.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KaKaoUserInfoDto {
    private Long id;
    private String email;
    private String username;
    private String nickname;

    public KaKaoUserInfoDto(Long id, String username, String nickname, String email){
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;

    }
}
