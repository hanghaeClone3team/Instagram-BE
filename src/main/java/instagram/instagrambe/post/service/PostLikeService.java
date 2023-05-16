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

    // 게시물에 좋아요를 누르는 메소드
    public ResponseEntity<PostResponseDto> LikePost(Long postId, User user) {
        // postId로 Post를 찾습니다. 만약 Post가 없다면 예외를 발생시킵니다.
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(NOT_FOUND_DATA));
        // 사용자가 이미 이 게시물에 좋아요를 눌렀는지 확인합니다.
        Long likeCheck = postLikeRepository.countByPostId_AndUser_UserId(postId, user.getUserId());
        boolean heart = false;
        if(likeCheck == 0){ // 좋아요를 누르지 않았으면,
            heart = true;
            System.out.println("00000heart = " + likeCheck);
            // 좋아요를 누릅니다.
            postService.likePost(post, heart);
            // 좋아요 정보를 저장합니다.
            PostLike like = postLikeRepository.saveAndFlush(new PostLike(post,user));
        }else if(likeCheck ==1) { // 이미 좋아요를 눌렀다면,
            System.out.println("11111heart = " + likeCheck);
            // 좋아요를 취소합니다.
            postService.likePost(post, heart);
            // 좋아요 정보를 삭제합니다.
            postLikeRepository.deleteByPostId_AndUser_UserId(postId, user.getUserId());
        }else {
            System.out.println("-----likeCheck = " + likeCheck);
            // 좋아요 수가 0 또는 1이 아니라면 예외를 발생시킵니다.
            throw new CustomException(DUPLICATE_RESOURCE);
        }

        // 게시물 정보를 응답으로 반환합니다.
        return ResponseEntity.ok().body(PostResponseDto.of(post));
    }
}
