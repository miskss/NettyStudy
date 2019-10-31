package course_7.course_6_stick_package.enums;

import course_7.course_6_stick_package.packet.LoginPacket;
import course_7.course_6_stick_package.packet.MessagePacket;
import course_7.course_6_stick_package.packet.Packet;
import course_7.course_6_stick_package.packet.ResponsePacket;

/**
 * @author peter
 * date: 2019-10-29 15:43
 **/
public enum CommandEnum {
    RESPONSE((byte) 0, ResponsePacket.class),
    LOGIN((byte) 1, LoginPacket.class),
    MESSAGE((byte) 2, MessagePacket.class),
    ;

    private byte code;

    private Class<? extends Packet> clazz;

    CommandEnum(byte code, Class<? extends Packet> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public byte getCode() {
        return code;
    }

    public Class<? extends Packet> getClazz() {
        return clazz;
    }

    public static CommandEnum fromCode(byte code) {
        CommandEnum[] values = CommandEnum.values();

        for (CommandEnum value : values) {

            if (value.code == code) return value;
        }

        return null;
    }
}
