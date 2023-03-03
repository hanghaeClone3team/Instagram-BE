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
    @PostMapping("/comment/{postId}")
    public BaseResponseDto createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable(name = "postId") Long postId,
            @RequestBody CommentResponseDto commentResponse
            ){
        return commentService.createComment(userDetails, postId, commentResponse);
    }

    // 댓글 수정


    // 댓글 삭제


    // 댓글 좋아요?


}
