package course_9.packet;

import course_9.enums.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author peter
 * date: 2019-10-30 10:27
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MessagePacket extends Packet {

    private String msg;

    @Override
    public Byte command() {
        return CommandEnum.MESSAGE.getCode();
    }




}
