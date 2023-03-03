package instagram.instagrambe.comment.controller;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.service.CommentReplyService;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentReplyController {

    private final CommentReplyService commentReplyService;

    // 대댓글 작성
    @PostMapping("/comment/reply/{comment_id}")
    public BaseResponseDto createCommentReply(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("comment_id") Long commentId,
            @RequestBody CommentResponseDto commentResponse
    ){
        return commentReplyService.createCommentReply(userDetails, commentId, commentResponse);
    }
}
