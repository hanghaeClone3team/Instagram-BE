package instagram.instagrambe.dto;

import instagram.instagrambe.util.SuccessCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponseDto {
    private String msg;
    private int statusCode;
    @Builder
    private BaseResponseDto(String msg, int statusCode)
    {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public static BaseResponseDto of(HttpStatus status, String msg)
    {
        return BaseResponseDto.builder()
                .statusCode(status.value())
                .msg(msg)
                .build();
    }
    public static BaseResponseDto of(SuccessCode successCode)
    {
        return BaseResponseDto.builder()
                .statusCode(successCode.getHttpStatus().value())
                .msg(successCode.getMsg())
                .build();
    }

}