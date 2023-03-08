package instagram.instagrambe.chat.controller;

import instagram.instagrambe.chat.config.redis.RedisPublisher;
import instagram.instagrambe.chat.model.ChatMessage;
import instagram.instagrambe.chat.service.ChatService;
import instagram.instagrambe.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final RoomService roomService;
    private final RedisPublisher redisPublisher;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            // redis 추가
            chatService.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        log.info("타입 : " + message.getType() + ", 대상 : " + message.getSender() + ", 메시지 : " + message.getMessage());
        // 메시지 저장
        roomService.saveMessage(message);
        // webSocket에서 발행된 메시지를 redis로 발행한다. (publish)
        redisPublisher.publish(chatService.getTopic(message.getRoomId()),message);
    }
}
