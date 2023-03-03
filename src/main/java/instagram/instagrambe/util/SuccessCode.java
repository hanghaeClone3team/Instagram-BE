package instagram.instagrambe.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
// ResponseStatusException 과 비슷해 보이지만, 개발자가 한번에 관리,재사용 할 수 있게 정리.
public enum SuccessCode {
    SIGNUP_SUCCESS(HttpStatus.OK, "회원가입 성공."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공."),
    BLOG_DELETE_SUCCESS(HttpStatus.OK,"게시글 삭제 성공"),
    COMMENT_DELETE_SUCCESS(HttpStatus.OK,"댓글 삭제 성공"),
    LIKE_SUCCESS(HttpStatus.OK, "좋아요 선택."),
    NOT_LIKE_SUCCESS(HttpStatus.OK, "좋아요 취소.");

    private final HttpStatus httpStatus;
    private final String msg;
}
