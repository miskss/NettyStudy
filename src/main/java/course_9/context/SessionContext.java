package course_9.context;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author peter
 * date: 2019-10-31 10:55
 **/
@Data
public class SessionContext {

    private User user;

    private Channel channel;

    public SessionContext(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }
}
