package instagram.instagrambe.follow.service;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.follow.repository.FollowRepository;
import instagram.instagrambe.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public void follow(Follow follow) {
        followRepository.save(follow);
    }
}
