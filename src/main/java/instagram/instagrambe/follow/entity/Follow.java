package instagram.instagrambe.follow.entity;

import instagram.instagrambe.chat.model.LoginInfo;
import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="follower_id")
    private User user;

    private String username;

    private Long following;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="Info")
    private LoginInfo loginInfo;

    public Follow(User follower, Long followingId){
        this.user = follower;
        this.username = follower.getUsername();
        this.following = followingId;
    }
}
