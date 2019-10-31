package course_9.packet;

import course_9.enums.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author peter
 * date: 2019-10-31 13:38
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ToReceiveMessagePacket extends Packet {
    private String username;

    private Long fromUserId;

    private String message;

    @Override
    public Byte command() {
        return CommandEnum.RECEIVE_MESSAGE.getCode();
    }
}
