package instagram.instagrambe.kakao.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import instagram.instagrambe.jwt.JwtUtil;
import instagram.instagrambe.kakao.dto.KaKaoUserInfoDto;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.user.entity.UserRoleEnum;
import instagram.instagrambe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String kaKaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        String accessToken = getToken(code);
        KaKaoUserInfoDto kaKaoUserInfoDto = getKaKaoUserInfo(accessToken);
        User kaKaoUser = registerKaKaoUserIfNeeded(kaKaoUserInfoDto);
        String createToken = jwtUtil.createToken(kaKaoUser.getUsername(), kaKaoUser.getRole());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);
        return createToken;
    }

    private String getToken(String code) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "tddc603749d78abff3f309e771a37719f");
        body.add("redirect_uri", "http://3.34.133.26:3030/api/user/kakao/login");
        // 프론트 서버 열면 바꾸기
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kaKaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kaKaoTokenRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private KaKaoUserInfoDto getKaKaoUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kaKaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kaKaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String username = jsonNode.get("username")
                .get("username").asText();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kaKao_account")
                .get("email").asText();

        log.info("카카오 사용자 정보: " + id + ", " + username + ", " + nickname + ", " + email);
        return new KaKaoUserInfoDto(id, username, nickname, email);
    }

    private User registerKaKaoUserIfNeeded(KaKaoUserInfoDto kaKaoUserInfo) {
        Long kaKaoId = kaKaoUserInfo.getId();
        User kaKaoUser = userRepository.findByKaKaoId(kaKaoId)
                .orElse(null);

        if (kaKaoUser == null) {
            String kaKaoEmail = kaKaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kaKaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kaKaoUser = sameEmailUser;

                kaKaoUser = kaKaoUser.kaKaoIdUpdate(kaKaoId);
            } else {
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);

                String email = kaKaoUserInfo.getEmail();

                kaKaoUser = new User(kaKaoUserInfo.getUsername(), kaKaoId, encodedPassword, email, UserRoleEnum.USER);
            }

            userRepository.save(kaKaoUser);
        }
        return kaKaoUser;
    }
}