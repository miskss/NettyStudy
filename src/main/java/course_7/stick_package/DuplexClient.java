package course_7.stick_package;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @author peter
 * date: 2019-10-22 09:06
 **/
class DuplexClient {

    public static void main(String[] args) {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        Bootstrap handler = new Bootstrap().group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DuplexClientHandler())
                        ;
                    }
                });

        connect(handler, "127.0.0.1", 8000);

    }

    private static void connect(Bootstrap handler, String ip, int port) {

        handler.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
                handler.config().group().shutdownGracefully();
            }
        });

    }
}
