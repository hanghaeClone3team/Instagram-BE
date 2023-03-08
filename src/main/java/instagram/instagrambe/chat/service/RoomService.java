package instagram.instagrambe.chat.service;

import instagram.instagrambe.chat.dto.FollowDto;
import instagram.instagrambe.chat.repository.MessageRepository;
import instagram.instagrambe.chat.repository.RoomRepository;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // DM방 입장
    public List<FollowDto> enterDMPage(UserDetailsImpl userDetails) {
        // 팔로우된 사람만 추출
        List<User> users = userRepository.findFollowingUsers(userDetails.getUser().getUserId());
        // Dto로 변환 후 반환
        return users.stream().map(FollowDto::new).collect(Collectors.toList());
    }
}
