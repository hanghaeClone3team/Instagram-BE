package instagram.instagrambe.comment.dto;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.entity.CommentReply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private Long comment_id;
    private String comments;
    private List<String> commentReplies = new ArrayList<>();

    public CommentResponseDto(Comment c) {
        this.comment_id = c.getId();
        this.comments = c.getComment();
    }

    public void addCommentReplies(CommentReply reply) {
        commentReplies.add(reply.getCommentReply());
    }

}
