package instagram.instagrambe.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom implements Serializable {

    private static final long serialVersionUUID = 6494678977089006639L;

    private String roomId;
    private String roomName;


    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
//        room.roomId = name + "Id";
        room.roomName = name;

        return room;
    }
}
