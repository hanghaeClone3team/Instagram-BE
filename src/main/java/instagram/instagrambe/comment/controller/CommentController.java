package instagram.instagrambe.comment.controller;

import instagram.instagrambe.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    CommentService commentService;
}
