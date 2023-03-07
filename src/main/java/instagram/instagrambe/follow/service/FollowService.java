package instagram.instagrambe.follow.service;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public ResponseEntity<Long> follow(User follower, Long userId) {
        Long followCheck = followRepository.countByUserAndFollowing(follower,userId);

//        boolean follow = false;
        if (followCheck == 0){
//            follow = true;
//            userService.follow(following, follow);
            followRepository.save(new Follow(follower, userId));
        } else if(followCheck == 1){
            followRepository.deleteByUserAndFollowing(follower, userId);
        } else throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        return ResponseEntity.ok().body(followCheck);
    }
}
