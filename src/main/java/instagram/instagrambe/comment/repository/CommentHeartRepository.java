package instagram.instagrambe.comment.repository;

import instagram.instagrambe.comment.entity.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Optional<CommentHeart> findCommentHeartByComment_IdAndUser_UserId(Long commentId, Long userId);
}
