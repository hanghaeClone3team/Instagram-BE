package instagram.instagrambe.chat.model;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "Info")
@NoArgsConstructor
public class LoginInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long Id;
    private String userName;
    @Column(nullable = false)
    private String token;

    @Builder
    public LoginInfo(String userName) { //, String token) {
        this.userName = userName;
        this.token = token;
    }
//    @ManyToOne
//    @JoinColumn(name="followId")
//    private Follow follow;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "follow")
//    private List<Follow> follow = new ArrayList<>();
}