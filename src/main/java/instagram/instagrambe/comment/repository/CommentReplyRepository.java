package instagram.instagrambe.comment.repository;

import instagram.instagrambe.comment.entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
}
