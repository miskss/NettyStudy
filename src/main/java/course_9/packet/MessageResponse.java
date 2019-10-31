package course_9.packet;

import course_9.enums.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author peter
 * date: 2019-10-31 14:46
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponse extends Packet {

    private String msg;

    public MessageResponse() {
    }

    public MessageResponse(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte command() {
        return CommandEnum.MESSAGE_RESPONSE.getCode();
    }
}
