package course_6.packet;

import course_6.enums.CommandEnum;
import lombok.Data;

/**
 * @author peter
 * date: 2019-10-29 17:04
 **/
@Data
public class ResponsePacket<T> extends Packet {
    @Override
    public Byte command() {
        return CommandEnum.RESPONSE.getCode();
    }

    private int code;

    private T data;

    public String errMsg;

    public boolean successful() {
        return code == 200;
    }

    public static <T> ResponsePacket<T> success(T data) {
        ResponsePacket<T> packet = new ResponsePacket<>();
        packet.setCode(200);
        packet.setData(data);
        packet.setErrMsg("成功");
        return packet;
    }

    public static ResponsePacket<?> fail(String errMsg) {
        ResponsePacket<?> packet = new ResponsePacket<>();
        packet.setCode(400);
        packet.setErrMsg(errMsg);
        return packet;
    }
}
