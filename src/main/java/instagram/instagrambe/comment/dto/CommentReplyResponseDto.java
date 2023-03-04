package instagram.instagrambe.comment.dto;

import instagram.instagrambe.comment.entity.CommentReply;

public class CommentReplyResponseDto extends CommentResponseDto {
    private String commentReply;

    public CommentReplyResponseDto(CommentReply r) { this.commentReply= r.getCommentReply();}

}
