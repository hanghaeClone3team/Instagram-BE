package instagram.instagrambe.comment.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private String id;

    @Column(nullable = false)
    private String comment;

    // post 연관관계

    // user 연관관계

}
