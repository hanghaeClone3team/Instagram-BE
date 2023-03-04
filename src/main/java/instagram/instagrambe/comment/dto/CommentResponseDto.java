package instagram.instagrambe.comment.dto;

import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.entity.CommentReply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private String comments;
    private List<String> commentReplies = new ArrayList<>();

    public CommentResponseDto(Comment c) {
        this.comments = c.getComment();
    }

    public void addCommentReplies(CommentReply reply) {
        commentReplies.add(reply.getCommentReply());
    }

}
