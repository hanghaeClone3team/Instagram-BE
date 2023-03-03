package instagram.instagrambe.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Long like;

    public Post(String content, String imageUrl, Long like) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.like = like;
    }
}
