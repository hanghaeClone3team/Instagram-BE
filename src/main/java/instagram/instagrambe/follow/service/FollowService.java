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
    public void follow(Long fromUserId, User toUser) {
        Follow follow = new Follow(fromUserId, toUser);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long fromUserId, User toUser){
        Follow follow = new Follow(fromUserId, toUser);
        followRepository.delete(follow);
    }
}