package instagram.instagrambe.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private String imageUrl;
    private LocalDateTime createAt;
    private Long likes;
}
