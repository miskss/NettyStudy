package course_4.packet;

import course_4.enums.CommandEnum;
import lombok.Builder;
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
