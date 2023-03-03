package instagram.instagrambe.post.repository;

import instagram.instagrambe.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    //Optional<Comment> findByIdAndUser(Long id, User user);
}
