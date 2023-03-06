package instagram.instagrambe.follow.service;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public void follow(User following, User follower) {
        Follow follow = new Follow(following, follower);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(User following, User follower){
        Follow follow = new Follow(following, follower);
        followRepository.delete(follow);
    }
}
