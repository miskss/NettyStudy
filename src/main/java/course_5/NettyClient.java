package course_5;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @author peter
 * date: 2019-10-30 11:23
 **/
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();


        ChannelFuture connect = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                }).connect("192.168.1.222", 8000);

        connect.addListener(future -> {
            if (future.isSuccess()) {
                ByteBuf buf = connect.channel().alloc().ioBuffer();
                buf.writeCharSequence("sddsaa", StandardCharsets.UTF_8);
                connect.channel().writeAndFlush(buf);
            }
        })
        ;

    }
}
