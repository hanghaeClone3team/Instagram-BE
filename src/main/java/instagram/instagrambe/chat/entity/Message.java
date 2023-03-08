package instagram.instagrambe.chat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String sender;
    private String message;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Message(Room room, String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.room = room;
    }
}
