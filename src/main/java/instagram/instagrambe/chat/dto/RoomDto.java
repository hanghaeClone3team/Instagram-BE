package instagram.instagrambe.chat.dto;

import instagram.instagrambe.chat.entity.Message;
import instagram.instagrambe.chat.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RoomDto {

    private Long room_id;
    private List<MessageDto> message_list = new ArrayList<>();

    public RoomDto(Room room) {
        this.room_id = room.getId();
    }

    public RoomDto(Room findRoom, List<Message> messages) {
        this(findRoom);
        this.message_list = messages.stream().map(MessageDto::new).collect(Collectors.toList());
    }
}
