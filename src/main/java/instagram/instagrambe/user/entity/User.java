package instagram.instagrambe.user.entity;

import instagram.instagrambe.user.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public User(SignupRequestDto requestDto, UserRoleEnum role) {
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.role = role;
    }
}
