package instagram.instagrambe.post.repository;

import instagram.instagrambe.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    Long countByPost_IdAndUser_Id(Long post_id, Long user_id);

    void deleteByPost_IdAndUser_Id(Long post_id, Long user_id);

}