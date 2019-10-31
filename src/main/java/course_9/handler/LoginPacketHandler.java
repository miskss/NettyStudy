package course_9.handler;

import course_9.context.SessionContext;
import course_9.context.SessionContextManager;
import course_9.context.User;
import course_9.context.UserPool;
import course_9.packet.LoginPacket;
import course_9.packet.ResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.io.IOException;
import java.util.Objects;

/**
 * @author peter
 * date: 2019-10-30 14:29
 **/
public class LoginPacketHandler extends SimpleChannelInboundHandler<LoginPacket> {

    private static final String LONGIN = "LONGIN";
    public static final AttributeKey<Object> LOGIN_KEY = AttributeKey.valueOf(LONGIN);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginPacket msg)  throws Exception{
        ResponsePacket responsePacket;
        try {
            User check = UserPool.check(msg.getUsername(), msg.getPassword());
            //保存到session
            ctx.channel().attr(LOGIN_KEY).set(true);
            SessionContextManager.put(check,ctx.channel());
            responsePacket = ResponsePacket.success("登录成功！！userId:" + check.getUserId());

        } catch (IOException e) {
            responsePacket = ResponsePacket.fail(e.getMessage());
        }

        ctx.channel().writeAndFlush(responsePacket);
    }



}
