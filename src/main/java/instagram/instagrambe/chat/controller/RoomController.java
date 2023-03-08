package instagram.instagrambe.chat.controller;

import instagram.instagrambe.chat.dto.FollowDto;
import instagram.instagrambe.chat.service.RoomService;
import instagram.instagrambe.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/enter")
    public List<FollowDto> enterDMPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.enterDMPage(userDetails);
    }
}
