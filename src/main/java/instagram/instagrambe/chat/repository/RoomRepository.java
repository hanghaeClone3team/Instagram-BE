package instagram.instagrambe.chat.repository;

import instagram.instagrambe.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r join r.follow f " +
            "where (f.user.userId = :userId and f.following = :selectUserId) " +
            "or (f.user.userId = :selectUserId and f.following = :userId)")
    Room findCreatedRoom(@Param("userId") Long userId, @Param("selectUserId") Long selectUserId);
}
