package instagram.instagrambe.comment.controller;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.service.CommentService;
import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.dto.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{id}")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment")
    public BaseResponseDto createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") Long postId,
            @RequestBody CommentResponseDto commentResponse
    ){
        return commentService.createComment(userDetails, postId, commentResponse);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{comment_id}")
    public BaseResponseDto deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") Long id,
            @PathVariable("comment_id") Long commentId
    ) {
        return commentService.deleteComment(userDetails, commentId, id);
    }

    // 댓글 좋아요
    @PostMapping("/comment/heart/{comment_id}")
    public BaseResponseDto postCommentHeart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("comment_id") Long id
    ) {
        return commentService.postCommentHeart(userDetails, id);
    }

}
