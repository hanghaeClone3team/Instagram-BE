package instagram.instagrambe.chat.repository;

import instagram.instagrambe.chat.entity.Message;
import instagram.instagrambe.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByRoom_Id(Long id);
}
