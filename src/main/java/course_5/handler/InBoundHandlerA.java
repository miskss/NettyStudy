package course_5.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author peter
 * date: 2019-10-30 11:31
 **/
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerA: " + msg);
        super.channelRead(ctx, msg);
        ByteBuf buf = ctx.channel().alloc().ioBuffer();
        buf.writeCharSequence("sddsqwqqwwqaa", StandardCharsets.UTF_8);
        ctx.channel().writeAndFlush(buf);

    }
}

