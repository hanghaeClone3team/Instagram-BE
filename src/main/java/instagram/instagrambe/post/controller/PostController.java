package instagram.instagrambe.post.controller;

import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.post.dto.PostRequestDto;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class PostController {
    private final PostService postService;

    // 게시글 작성
    public ResponseEntity<PostResponseDto> createPost(@RequestPart MultipartFile image,
                                                      @RequestPart PostRequestDto postRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        System.out.println("-------image.getOriginalFilename() = " + image.getOriginalFilename());
        System.out.println("-------image.getContentType() = " + image.getContentType());
        System.out.println("-------requestDtoContents = " + postRequestDto.getContents());
    return postService.createPost(image, postRequestDto, userDetails.getUser());
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPosts(userDetails.getUser());
    }

    // 게시글 상세 조회
    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPost(post_id, userDetails.getUser());
    }

    // 선택한 게시글 수정
    @PatchMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> updateBlog(@PathVariable Long post_id, @RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updateBlog(post_id, postrequestDto, userDetails.getUser());
    }

    // 선택한 게시글 삭제
    @DeleteMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> deleteBlog(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deleteBlog(post_id, userDetails.getUser());
    }
}

