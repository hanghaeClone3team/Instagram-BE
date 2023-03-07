package instagram.instagrambe.user.dto;

import instagram.instagrambe.user.entity.User;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {
    private Long id;
    private String email;
    private String userName;
    private List<String> userInterested; // 빈 배열
//    private String token;
//    private List<String> roles = new ArrayList<>();
    public UserDto(String token, User user){
//        this.id =user.getId();
        this.email = user.getEmail();
        this.userName = user.getUsername();
//        this.userInterested = user.getUserInterested();
//        this.roles = user.getRoles();
//        this.token = token;
    }
}
