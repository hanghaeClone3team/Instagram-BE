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
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment/{id}")
    public BaseResponseDto createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") Long postId,
            @RequestBody CommentResponseDto commentResponse
    ){
        return commentService.createComment(userDetails, postId, commentResponse);
    }

    // 댓글 삭제
    @DeleteMapping("/comment")
    public BaseResponseDto deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("id") Long id,
            @RequestParam("comment_id") Long commentId
    ) {
        return commentService.deleteComment(userDetails, commentId, id);
    }

    // 댓글 좋아요


}
