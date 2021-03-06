package course_8.handler;

import course_8.packet.Packet;
import course_8.packet.PacketCodec;
import course_8.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author peter
 * date: 2019-10-30 14:36
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    private PacketCodec packetCodec = new PacketCodec(new JsonSerizalizer());

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        packetCodec.encode(out, msg);
    }
}
