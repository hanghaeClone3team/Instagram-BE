package instagram.instagrambe.comment.dto;

import instagram.instagrambe.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private String comments;

    public CommentResponseDto(Comment c) {
        this.comments = c.getComment();
    }
}
