package course_4.packet;

import lombok.Data;

/**
 * @author peter
 * date: 2019-10-29 15:41
 **/
@Data
public abstract class Packet {

    /**
     * 版本
     */
    private Byte version = 1;

    /**
     * 指令
     *
     * @return Byte
     */
    public abstract Byte command();

}
