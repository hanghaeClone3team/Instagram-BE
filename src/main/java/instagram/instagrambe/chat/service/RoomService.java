package instagram.instagrambe.chat.service;

import instagram.instagrambe.chat.dto.FollowDto;
import instagram.instagrambe.chat.dto.RoomDto;
import instagram.instagrambe.chat.dto.TestDto;
import instagram.instagrambe.chat.entity.Message;
import instagram.instagrambe.chat.entity.Room;
import instagram.instagrambe.chat.model.ChatMessage;
import instagram.instagrambe.chat.repository.MessageRepository;
import instagram.instagrambe.chat.repository.RoomRepository;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // DM방 입장
    public List<FollowDto> enterDMPage(UserDetailsImpl userDetails) {
        // 팔로우된 사람만 추출
        List<User> users = userRepository.findFollowingUsers(userDetails.getUser().getUserId());
        // Dto로 변환 후 반환
        return users.stream().map(FollowDto::new).collect(Collectors.toList());
    }

    public RoomDto enterChatRoom(UserDetailsImpl userDetails, Long selectUserId) {
        Long userId = userDetails.getUser().getUserId();
        // 방이 있는지 확인
        Room findRoom = roomRepository.findCreatedRoom(userId, selectUserId);
        // 팔로우 여부 확인
        Follow follow = followRepository.findByUser_UserIdAndFollowing(userId, selectUserId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_FOLLOW));
        RoomDto roomDto = null;
        // 없으면 방 생성
        if (findRoom == null) {
            Room room = new Room(follow);
            roomRepository.save(room);
            roomDto = new RoomDto(room);
        }else {
            // 메시지 리스트 출력
            List<Message> messages = messageRepository.findMessagesByRoom_Id(findRoom.getId());
            roomDto = new RoomDto(findRoom, messages);
        }
        return roomDto;
    }

    // 메시지 저장
    public void saveMessage(ChatMessage message) {
        Room room = roomRepository.findById(Long.valueOf(message.getRoomId())).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_DATA)
        );
        messageRepository.save(new Message(room, message.getSender(), message.getMessage()));
    }
}
