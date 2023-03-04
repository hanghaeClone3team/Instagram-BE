package instagram.instagrambe.follow.repository;

import instagram.instagrambe.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
