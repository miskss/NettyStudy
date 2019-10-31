package course_8.packet;

import course_8.enums.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author peter
 * date: 2019-10-29 15:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte command() {
        return CommandEnum.LOGIN.getCode();
    }

    public LoginPacket() {
    }
}
