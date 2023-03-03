package instagram.instagrambe.user.repository;

import instagram.instagrambe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
