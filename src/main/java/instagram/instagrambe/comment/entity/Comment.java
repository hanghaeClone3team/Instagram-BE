package instagram.instagrambe.comment.entity;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.common.Timestamped;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private String id;

    @Column(nullable = false)
    private String comment;

    // post 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // user 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(User user, Post post, CommentResponseDto response) {
        this.comment = response.getComments();
        this.post = post;
        this.user = user;
    }
}
