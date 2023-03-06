package instagram.instagrambe.follow.entity;

import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User following;


    public Follow(User follower, User following){
        this.follower = follower;
        this.following = following;
    }
}
