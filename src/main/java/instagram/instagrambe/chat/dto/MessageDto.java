package instagram.instagrambe.chat.dto;

import instagram.instagrambe.chat.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {

    private Long message_id;
    private String sender;
    private String message;

    public MessageDto(Message message) {
        this.message_id = message.getId();
        this.sender = message.getSender();
        this.message = message.getMessage();
    }
}
