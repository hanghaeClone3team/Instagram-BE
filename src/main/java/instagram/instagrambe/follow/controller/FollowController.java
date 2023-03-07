package instagram.instagrambe.follow.controller;

import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.follow.service.FollowService;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    @PostMapping("/follow/{userId}")
    public ResponseEntity<Long> follow(@PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails){
        User follower = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_CLIENT));
        userRepository.findByUserId(userId).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_CLIENT));

        if (follower.equals(userId)) throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");

        return followService.follow(follower, userId);
    }
}
