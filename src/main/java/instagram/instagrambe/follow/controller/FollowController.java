package instagram.instagrambe.follow.controller;

import instagram.instagrambe.follow.service.FollowService;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("/api/post")
    public class FollowController {
        private FollowService followService;
        private UserRepository userRepository;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody Long fromUserId, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_CLIENT));
        followService.follow(fromUserId, user);
        return ResponseEntity.ok().body("팔로우");
    }

    @DeleteMapping("/follow")
    public ResponseEntity<String> unfollow(@RequestBody Long fromUserId, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_CLIENT));
        followService.unfollow(fromUserId, user);
        return ResponseEntity.ok().body("언팔로우");
    }
}
