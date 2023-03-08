package instagram.instagrambe.chat.entity;

import instagram.instagrambe.follow.entity.Follow;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follow_id")
    private Follow follow;

    public Room(Follow follow) {
        this.follow = follow;
    }
}
