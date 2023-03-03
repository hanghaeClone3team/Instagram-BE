package instagram.instagrambe.post.controller;

import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.entity.PostLike;
import instagram.instagrambe.post.repository.PostLikeRepository;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.post.service.PostLikeService;
import instagram.instagrambe.post.service.PostService;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static instagram.instagrambe.util.ErrorCode.DUPLICATE_RESOURCE;
import static instagram.instagrambe.util.ErrorCode.NOT_FOUND_DATA;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class PostLikeController {
   private final PostLikeService postLikeService;
   @PostMapping("/{post_id}/like")
   public ResponseEntity<PostResponseDto> likePost(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return postLikeService.LikePost(post_id, userDetails.getUser());
   }
}
