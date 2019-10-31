package course_5;

import course_5.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author peter
 * date: 2019-10-30 11:15
 **/
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();


        ChannelFuture bind = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new InBoundHandlerA())
                                .addLast(new InBoundHandlerB())
                                .addLast(new InBoundHandlerC());

                        ch.pipeline()
                                .addLast(new OutBoundHandlerC())
                                .addLast(new OutBoundHandlerB())
                                .addLast(new OutBoundHandlerA());

                    }
                }).bind(8000);


    }
}
