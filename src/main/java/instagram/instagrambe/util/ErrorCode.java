package instagram.instagrambe.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_TOKEN(BAD_REQUEST, "토큰이 유효하지 않습니다"),
    MISMATCH_TOKEN(BAD_REQUEST, "토큰의 유저 정보가 일치하지 않습니다"),
    INVALIDATION_SIGNUP(BAD_REQUEST, "username과 password의 형식을 지켜주세요."),
    INVALIDATION_PASSWORD(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NO_FOLLOW(BAD_REQUEST, "팔로우 관계가 아닙니다."),


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),


    /* 403 NOT_FOUND : Resource 를 찾을 수 없음 */
    FORBIDDEN_DATA(FORBIDDEN, "권한이 없습니다."),


    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    EMPTY_CLIENT(NOT_FOUND, "등록된 유저가 없습니다."),
    NOT_FOUND_CLIENT(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    NOT_FOUND_DATA(NOT_FOUND,"해당 게시물을 찾을 수 없습니다."),
    NOT_FOUND_COMMENT(NOT_FOUND,"해당 댓글을 찾을 수 없습니다."),

    /*404 NOT_FOUND : TOKEN Error*/
    NOT_FOUND_TOKEN (HttpStatus.NOT_FOUND, "토큰을 찾을 수 없습니다."),
    NOT_INVALID_JWT (HttpStatus.NOT_FOUND, "유효하지 않는 JWT 서명 입니다."),
    EXPIRED_TOKEN (HttpStatus.NOT_FOUND, "만료된 JWT 토큰 입니다."),
    UNSUPPORTED_TOKEN (HttpStatus.NOT_FOUND, "지원되지 않는 JWT 토큰 입니다."),
    WRONG_TOKEN (HttpStatus.NOT_FOUND, "잘못된 JWT 토큰 입니다.."),
    NO_AUTHORITY(HttpStatus.NOT_FOUND, "토큰을 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
    DUPLICATE_MEMBER(CONFLICT, "중복된 사용자가 존재합니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}


