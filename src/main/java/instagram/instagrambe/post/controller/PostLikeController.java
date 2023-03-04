package instagram.instagrambe.post.controller;

import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostLikeController {
   private final PostLikeService postLikeService;
   @PostMapping("/{postId}/like")
   public ResponseEntity<PostResponseDto> likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return postLikeService.LikePost(postId, userDetails.getUser());
   }
}

