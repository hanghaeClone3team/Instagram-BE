package instagram.instagrambe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import instagram.instagrambe.dto.BaseResponseDto;
import instagram.instagrambe.util.CustomException;
import instagram.instagrambe.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (CustomException e) {
            customExceptionHandler(response, e.getErrorCode());
        }
    }

    private void customExceptionHandler(HttpServletResponse response, ErrorCode errorCode) {
        log.info(errorCode.getDetail());
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(BaseResponseDto.of(errorCode.getHttpStatus(), errorCode.getDetail()));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}