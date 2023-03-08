package instagram.instagrambe.follow.repository;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, User> {

    Long countByUserAndFollowing(User follower, Long followingId);
    void deleteByUserAndFollowing(User follower, Long followingId);
    Optional<Follow> findByUser_UserIdAndFollowing(Long userId, Long selectedUserId);
}
