package instagram.instagrambe.post.image.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    private String imageUrl;

    public ImageEntity(String title, String contents, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
    }
}