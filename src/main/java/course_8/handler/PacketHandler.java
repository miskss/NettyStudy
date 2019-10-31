package course_8.handler;

import course_8.packet.Packet;
import course_8.packet.PacketCodec;
import course_8.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author peter
 * date: 2019-10-30 14:26
 **/
public class PacketHandler extends ByteToMessageDecoder {

    private PacketCodec packetCodec = new PacketCodec(new JsonSerizalizer());


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet decode = packetCodec.decode(in);
        out.add(decode);
    }
}
