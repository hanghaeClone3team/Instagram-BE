package instagram.instagrambe.comment.service;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.repository.CommentRepository;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.dto.BaseResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.repository.UserRepository;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import instagram.instagrambe.util.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public BaseResponseDto createComment(UserDetailsImpl userDetails, Long postId, CommentResponseDto response) {
        // 아이디 유무 확인
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new CustomException(ErrorCode.EMPTY_CLIENT)
        );
        // post 유뮤 확인
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_DATA)
        );
        // comment 작성
        Comment comment = new Comment(user, post, response);
        commentRepository.save(comment);
        // 상태 반환
        return BaseResponseDto.of(SuccessCode.COMMENT_POST_SUCCESS);
    }
}
