package course_8.handler;

import course_8.packet.LoginPacket;
import course_8.packet.ResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.Objects;

/**
 * @author peter
 * date: 2019-10-30 14:29
 **/
public class LoginPacketHandler extends SimpleChannelInboundHandler<LoginPacket> {

    private static final String LONGIN = "LONGIN";
    public static final AttributeKey<Object> LOGIN_KEY = AttributeKey.valueOf(LONGIN);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginPacket msg) throws Exception {
        ResponsePacket responsePacket;
        if (Objects.equals(msg.getPassword(), "11") && Objects.equals(msg.getUsername(), "22")) {
            responsePacket = ResponsePacket.success(1);
            ctx.channel().attr(LOGIN_KEY).set(true);
        } else {
            responsePacket = ResponsePacket.fail("账号或密码不正确");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }
}
