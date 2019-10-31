package course_9.server;

import course_9.handler.*;
import course_9.unpack.UnpackSpliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author peter
 * date: 2019-10-29 17:07
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
                        ch.pipeline()
                                .addLast(new ChannelLifeCircleHandler())
                                .addLast(new UnpackSpliter())
                                .addLast(new PacketHandler())
                                .addLast(new LoginPacketHandler())
                                .addLast(new AuthCheckHandler())
                                .addLast(new MessagePacketHandler())
                                .addLast(new UserMessageHandler())
                                .addLast(new PacketEncoder());
                    }
                })
                .bind(8000);


    }


}
