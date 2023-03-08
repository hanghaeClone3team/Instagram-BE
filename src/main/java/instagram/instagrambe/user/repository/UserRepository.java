package instagram.instagrambe.user.repository;

import instagram.instagrambe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByKakaoId(Long id);
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);

    @Query("select u from users u where u.userId in (select f.following from Follow f where f.user.userId = :id)")
    List<User> findFollowingUsers(@Param(value = "id") Long userId);
}
