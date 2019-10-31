package course_9.context;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peter
 * date: 2019-10-31 10:58
 **/
public class UserPool {

    public static final ConcurrentHashMap<Long, User> USERS = new ConcurrentHashMap<>(2);

    static {
        User user = new User(1L, "22", "11");

        User user1 = new User(2L, "222", "111");

        USERS.put(user.getUserId(), user);

        USERS.put(user1.getUserId(), user1);
    }

    public static User getOne(Long userId) {
        return USERS.get(userId);
    }

    public static User check(String username, String password) throws IOException {

        Optional<User> first = USERS.values().stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst();

        User user = first.orElseThrow(() -> new IOException("username 不正确"));

        if (!Objects.equals(user.getPassword(), password)) {

            throw new IOException("password 不正确");

        }

        return user;
    }

}
