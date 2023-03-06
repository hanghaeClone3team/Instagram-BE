package instagram.instagrambe.follow.repository;

import instagram.instagrambe.follow.entity.Follow;
import instagram.instagrambe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, User> {

    Follow findByFollowerAndFollowing(User follower, User following);
    Follow deleteByFollowerAndFollowing(User follower, User following);
}
