package course_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author peter
 * date: 2019-10-22 09:07
 **/
class DuplexClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + ":开始向服务端写数据");

        ByteBuf byteBuf = getByteBuf(ctx);

        ctx.channel().writeAndFlush(byteBuf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(LocalDateTime.now()+": 收到服务器的消息");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(StandardCharsets.UTF_8));

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "你好，netty!".getBytes(StandardCharsets.UTF_8);

        buffer.writeBytes(bytes);

        return buffer;
    }
}
