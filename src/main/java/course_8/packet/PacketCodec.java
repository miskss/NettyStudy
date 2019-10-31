package course_8.packet;

import course_8.enums.CommandEnum;
import course_8.enums.SerializedTypeEnum;
import course_8.serializer.Serializer;
import io.netty.buffer.ByteBuf;

/**
 * 封包，编码
 *
 * @author peter
 * date: 2019-10-29 16:03
 **/
public class PacketCodec {

    private Serializer serializer;
    //魔数
    public static final int MAGIC_NUMBER = 0x12345678;

    public PacketCodec(Serializer serializer) {
        this.serializer = serializer;
    }

    public ByteBuf encode(ByteBuf buf, Packet packet) {

        buf.writeInt(MAGIC_NUMBER);

        buf.writeByte(packet.getVersion());

        buf.writeByte(serializer.getSerializerAlgorithm());

        buf.writeByte(packet.command());
        byte[] serialize = serializer.serialize(packet);
        buf.writeInt(serialize.length);

        buf.writeBytes(serialize);

        return buf;
    }


    public Packet decode(ByteBuf buf) {

        buf.skipBytes(4);
        buf.skipBytes(1);
        byte serializedType = buf.readByte();
        byte command = buf.readByte();
        int dataLength = buf.readInt();

        byte[] data = new byte[dataLength];

        buf.readBytes(data);

        SerializedTypeEnum serializedTypeEnum = SerializedTypeEnum.fromCode(serializedType);

        CommandEnum commandEnum = CommandEnum.fromCode(command);

        if (serializedTypeEnum == null || commandEnum == null) return null;

        return serializedTypeEnum.getSerializer().deserialize(commandEnum.getClazz(), data);
    }


}
