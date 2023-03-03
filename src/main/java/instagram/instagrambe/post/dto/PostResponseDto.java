package instagram.instagrambe.post.dto;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Long likes;
    private boolean heart;
    private List<CommentResponseDto> comments;
    @Builder
    public PostResponseDto(Post post, List<CommentResponseDto> comments, boolean heart) {
        this.id = post.getId();
        this.contents = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.imageUrl=post.getImageUrl();
        this.comments = comments;
        this.likes = post.getPostlikes();
        this.heart = heart;
    }
    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .post(post)
                .build();
    }
}
