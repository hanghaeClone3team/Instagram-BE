package instagram.instagrambe.chat.entity;

import instagram.instagrambe.follow.entity.Follow;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follow_id")
    private Follow follow;
}
