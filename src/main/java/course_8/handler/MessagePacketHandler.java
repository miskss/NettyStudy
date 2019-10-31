package course_8.handler;

import course_8.packet.MessagePacket;
import course_8.packet.ResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author peter
 * date: 2019-10-30 14:31
 **/
public class MessagePacketHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket msg) throws Exception {
        String msg1 = msg.getMsg();
        ResponsePacket<String> success = ResponsePacket.success("服务器回应:" + msg1);
        ctx.channel().writeAndFlush(success);

    }
}
