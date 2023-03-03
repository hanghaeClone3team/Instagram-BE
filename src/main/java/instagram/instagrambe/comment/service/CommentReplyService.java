package instagram.instagrambe.comment.service;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.entity.CommentReply;
import instagram.instagrambe.comment.repository.CommentReplyRepository;
import instagram.instagrambe.comment.repository.CommentRepository;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.dto.BaseResponseDto;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import instagram.instagrambe.util.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReplyService {

    private final CommentReplyRepository commentReplyRepository;
    private final CommentRepository commentRepository;

    // 대댓글 작성
    public BaseResponseDto createCommentReply(UserDetailsImpl userDetails, Long commentId, CommentResponseDto commentResponse) {
        User user = userDetails.getUser();
        Comment comment = getComment(commentId);
        // commentReply 작성
        CommentReply commentReply = new CommentReply(user, comment, commentResponse.getComments());
        commentReplyRepository.save(commentReply);
        // 상태 반환
        return BaseResponseDto.of(SuccessCode.COMMENT_POST_SUCCESS);
    }

    // comment 유무 확인
    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
    }
}
