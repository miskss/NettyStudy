package course_6.handler;

import course_6.packet.LoginPacket;
import course_6.packet.ResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

/**
 * @author peter
 * date: 2019-10-30 14:29
 **/
public class LoginPacketHandler extends SimpleChannelInboundHandler<LoginPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginPacket msg) throws Exception {
        ResponsePacket responsePacket;
        if (Objects.equals(msg.getPassword(), "11") && Objects.equals(msg.getUsername(), "22")) {
            responsePacket = ResponsePacket.success(1);
        } else {
            responsePacket = ResponsePacket.fail("账号或密码不正确");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }
}
