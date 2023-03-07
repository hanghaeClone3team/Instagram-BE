package instagram.instagrambe.user.entity;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private Long kakaoId;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Post> post = new ArrayList<>();

//    private List<Follow> follower = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Follow> following = new ArrayList<>();

    public User(String username, String nickname, String email, String password, UserRoleEnum role) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String username, Long kakaoId, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.kakaoId = kakaoId;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User kakaoIdUpdate(Long kakaoId){
        this.kakaoId = kakaoId;
        return this;
    }
}
