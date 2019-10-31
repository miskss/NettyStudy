package course_9.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;

/**
 * @author peter
 * date: 2019-10-31 09:05
 **/
public class AuthCheckHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object o = ctx.channel().attr(LoginPacketHandler.LOGIN_KEY).get();
        if (Objects.nonNull(o)) {
            //已经登录，移除权限的验证handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            //未登录，关闭连接
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Object o = ctx.channel().attr(LoginPacketHandler.LOGIN_KEY).get();
        if (Objects.nonNull(o)) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthCheckHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
