package instagram.instagrambe.post.repository;

import instagram.instagrambe.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    Long countByPostId_AndUser_UserId(Long postId, Long userId);

    void deleteByPostId_AndUser_UserId(Long postId, Long userId);

}