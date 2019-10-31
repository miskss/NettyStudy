package course_7.course_6_stick_package.packet;

import course_7.course_6_stick_package.enums.CommandEnum;
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
