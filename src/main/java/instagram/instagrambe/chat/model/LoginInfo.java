package instagram.instagrambe.chat.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginInfo {
    private String userName;
//    private String token;

    @Builder
    public LoginInfo(String userName) { //, String token) {
        this.userName = userName;
//        this.token = token;
    }
}