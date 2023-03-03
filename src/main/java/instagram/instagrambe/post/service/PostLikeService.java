package instagram.instagrambe.post.service;

import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.entity.PostLike;
import instagram.instagrambe.post.repository.PostLikeRepository;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static instagram.instagrambe.util.ErrorCode.DUPLICATE_RESOURCE;
import static instagram.instagrambe.util.ErrorCode.NOT_FOUND_DATA;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;

    public ResponseEntity<PostResponseDto> LikePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(NOT_FOUND_DATA));
        Long likeCheck = postLikeRepository.countByPostIdAndUserId(postId, user.getUserId());
        boolean heart = false;
        if(likeCheck == 0){ //좋아용
            heart = true;
            System.out.println("00000heart = " + likeCheck);
            postService.likePost(post, heart);
            PostLike like = postLikeRepository.saveAndFlush(new PostLike(post,user));
        }else if(likeCheck ==1) { //좋아요
            System.out.println("11111heart = " + likeCheck);
            postService.likePost(post, heart);
            postLikeRepository.deleteByPostIdAndUserId(postId, user.getUserId());
        }else {
            System.out.println("-----likeCheck = " + likeCheck);
            throw new CustomException(DUPLICATE_RESOURCE);
        }

        return ResponseEntity.ok().body(PostResponseDto.of(post));
    }
}
