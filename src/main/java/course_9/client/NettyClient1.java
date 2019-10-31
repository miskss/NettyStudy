package course_9.client;

import course_9.handler.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author peter
 * date: 2019-10-31 13:49
 **/
public class NettyClient1 {
    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap handler = new Bootstrap()
                .group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new PacketHandler())
                                .addLast(new ResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new ToReceiveMessageHandler())
                                .addLast(new PacketEncoder());
                    }
                });


        ChannelFuture connect = handler.connect("192.168.1.222", 8000);

        ResponseHandler.login(connect.channel());
    }
}
