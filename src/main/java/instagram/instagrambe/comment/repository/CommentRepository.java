package instagram.instagrambe.comment.repository;

import instagram.instagrambe.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
