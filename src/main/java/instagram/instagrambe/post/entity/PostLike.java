package instagram.instagrambe.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "blogLike")
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

//    @ManyToOne
//    @JoinColumn(name="user_no")
//    private User user;

//    public Like(Post blog, User user) {
//        this.post = blog;
//        this.user = user;
//    }
}
