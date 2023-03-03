package instagram.instagrambe.post.controller;

import instagram.instagrambe.config.UserDetailsImpl;
import instagram.instagrambe.post.dto.PostRequestDto;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<PostResponseDto>> getBlogs(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPosts(userDetails.getUser());
    }
}

