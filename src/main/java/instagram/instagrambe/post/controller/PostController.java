package instagram.instagrambe.post.controller;

import instagram.instagrambe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class PostController {
    private final PostService postService;
}
