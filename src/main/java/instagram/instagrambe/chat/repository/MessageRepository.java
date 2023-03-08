package instagram.instagrambe.chat.repository;

import instagram.instagrambe.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
