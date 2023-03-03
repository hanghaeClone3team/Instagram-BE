package instagram.instagrambe.post.entity;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.post.dto.PostRequestDto;
import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    private String imageUrl;
    private Long postlikes;

    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "blog")
    private List<PostLike> postlike = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();


    public Post(PostRequestDto postRequestDto, User user) {
        this.content = postRequestDto.getContents();
        this.user = user;
        this.imageUrl = postRequestDto.getImageUrl();
        this.postlikes = 0L;
    }

    public void like(Long likes){
        this.postlikes = likes;
    }

    public void update(PostRequestDto blogRequestDto) {
        this.content = blogRequestDto.getContents();
    }
}
