package course_9.packet;

import course_9.enums.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户发送用户消息
 *
 * @author peter
 * date: 2019-10-31 10:49
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserMessagePacket extends Packet {

    private Long toUserId;

    private String message;

    @Override
    public Byte command() {
        return CommandEnum.USER_MESSAGE.getCode();
    }
}
