package course_9.context;

import io.netty.channel.Channel;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peter
 * date: 2019-10-31 11:02
 **/
public class SessionContextManager {

    private final static ConcurrentHashMap<Long, SessionContext> SESSIONS = new ConcurrentHashMap<>(2);


    public static void put(User user, Channel channel) {

        SessionContext sessionContext = new SessionContext(user, channel);

        SESSIONS.put(user.getUserId(), sessionContext);
    }

    public static SessionContext getOne(Long userId) {
        return SESSIONS.get(userId);
    }

    public static User fromChannel(Channel channel) {

        Optional<SessionContext> first = SESSIONS.values().stream()
                .filter(sessionContext -> Objects.equals(sessionContext.getChannel(), channel))
                .findFirst();

        SessionContext sessionContext = first.orElse(null);

        return sessionContext == null ? null : sessionContext.getUser();

    }
}
