package instagram.instagrambe.chat.controller;

import instagram.instagrambe.chat.dto.FollowDto;
import instagram.instagrambe.chat.dto.RoomDto;
import instagram.instagrambe.chat.dto.TestDto;
import instagram.instagrambe.chat.model.ChatMessage;
import instagram.instagrambe.chat.service.RoomService;
import instagram.instagrambe.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {

    private final RoomService roomService;

    // DM 방 입장
    @GetMapping("/enter")
    public List<FollowDto> enterDMPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.enterDMPage(userDetails);
    }

    // 채팅방 입장
    @GetMapping("/detail/{user_id}")
    public RoomDto enterChatRoom(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable(value = "user_id") Long selectUserId) {
        return roomService.enterChatRoom(userDetails, selectUserId);
    }

    // 대화 입력 테스트
    @PostMapping("/test")
    public void chatTest(@RequestBody ChatMessage message) {
        roomService.saveMessage(message);
    }
}
