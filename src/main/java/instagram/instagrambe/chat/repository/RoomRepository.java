package instagram.instagrambe.chat.repository;

import instagram.instagrambe.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
