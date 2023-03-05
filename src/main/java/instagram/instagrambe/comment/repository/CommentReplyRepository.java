package instagram.instagrambe.comment.repository;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
    List<CommentReply> findAllByCommentOrderByCreatedAtDesc(Comment comment);
}
