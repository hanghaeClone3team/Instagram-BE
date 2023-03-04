package instagram.instagrambe.follow.entity;

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

    private Long fromUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User toUserId;

    public Follow(Long fromUserId, User toUserId){
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}
