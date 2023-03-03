package instagram.instagrambe.comment.repository;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogOrderByCreateAtDesc(Post post);
    //void deleteAllByBlog(Post post);
}
