package instagram.instagrambe.comment.entity;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.common.Timestamped;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(name = "comment_likes")
    Long commentLikes;

    // post 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // user 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // heart 연관관계
//    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
//    private List<Comment> comments = new ArrayList<>();

    // commentReply 연관관계
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentReply> commentReplies = new ArrayList<>();

    public Comment(User user, Post post, CommentResponseDto response) {
        this.comment = response.getComments();
        this.post = post;
        this.user = user;
        this.commentLikes = 0L;
    }

    public void addHeart() {
        this.commentLikes += 1L;
    }

    public void deleteHeart() {
        this.commentLikes -= 1L;
    }
}
