package instagram.instagrambe.comment.service;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.repository.CommentRepository;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.dto.BaseResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import instagram.instagrambe.util.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    public BaseResponseDto createComment(UserDetailsImpl userDetails, Long postId, CommentResponseDto response) {
        User user = userDetails.getUser();
        Post post = getPost(postId);
        // comment 작성
        Comment comment = new Comment(user, post, response);
        commentRepository.save(comment);
        // 상태 반환
        return BaseResponseDto.of(SuccessCode.COMMENT_POST_SUCCESS);
    }

    // 댓글 삭제
    public BaseResponseDto deleteComment(UserDetailsImpl userDetails, Long commentId, Long postId) {
        Post post = getPost(postId);
        Comment comment = getComment(commentId);
        checkValidation(post, comment, userDetails);
        // 삭제
        commentRepository.delete(comment);
        // 상태 반환
        return BaseResponseDto.of(SuccessCode.COMMENT_DELETE_SUCCESS);
    }

    /* == 반복 로직 == */

    // post 유무 확인
    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DATA));
    }

    // comment 유무 확인
    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
    }

    // comment 유효성 검사
    private void checkValidation(Post post, Comment comment, UserDetailsImpl userDetails) {
        // post에 해당 comment가 있는지 검사
        if(!comment.getPost().getId().equals(post.getId()))
            throw new CustomException(ErrorCode.NOT_FOUND_COMMENT);
        // comment 작성자와 요청자의 일치 여부 검사
        if(!comment.getUser().getId().equals(userDetails.getUser().getId()))
            throw new CustomException(ErrorCode.UNAUTHORIZED_MEMBER);
    }
}
