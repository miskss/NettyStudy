package course_9.context;

import lombok.Data;

/**
 * @author peter
 * date: 2019-10-31 10:56
 **/
@Data
public class User {

    private Long userId;
    private String username;
    private String password;

    public User(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
