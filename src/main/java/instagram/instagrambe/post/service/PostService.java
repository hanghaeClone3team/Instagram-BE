package instagram.instagrambe.post.service;

import instagram.instagrambe.comment.repository.CommentRepository;
import instagram.instagrambe.post.dto.PostRequestDto;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.image.common.S3Uploader;
import instagram.instagrambe.post.repository.PostLikeRepository;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;
    private final PostLikeRepository likeRepository;
    private final CommentRepository commentRepository;

    // 게시글 작성
    public ResponseEntity<PostResponseDto> createPost(MultipartFile image,
                                                      PostRequestDto postRequestDto,
                                                      User user) throws IOException {
        System.out.println("postRepository.contents = " + postRepository.getContents());
        System.out.println("-------user = " + user.getUsername());
        String storedFileName = s3Uploader.upload(image, "images"); //s3에 업로드하기

        postRequestDto.setImageUrl(storedFileName);

        Post post = postRepository.saveAndFlush(new Post(postRequestDto, user));
        return ResponseEntity.ok().body(PostResponseDto.of(post));

    }
}


