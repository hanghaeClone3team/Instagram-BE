package instagram.instagrambe.chat.dto;

import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowDto {

    private Long user_id;
    private String nickname;
    private String username;

    public FollowDto(User user) {
        this.user_id = user.getUserId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
    }
}
