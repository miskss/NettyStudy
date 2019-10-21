package course_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author peter
 * date: 2019-10-21 16:38
 **/
public class NettyClient {

    private static int retry = 0;

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap handler = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });

//
        String host = "127.0.0.1";
        int port = 8088;
        int maxRetry = 5;
        ChannelFuture connect = connect(handler, host, port, maxRetry);
        connect.channel().writeAndFlush("xiao");
//        connect.channel().close();

    }

    private static ChannelFuture connect(final Bootstrap handler, final String host, final int port, final int maxRetry) {


        return handler.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                String s = LocalDateTime.now().toString();
                System.err.println(s + "连接失败!");

                retry++;
                if (retry == maxRetry) {
                    System.err.println("重试次数已到，不在发起连接");
                    return;
                }
                System.out.println(retry);
                long pow = 1 << retry;
                System.out.println(pow);

                handler.config().group().schedule(() -> {
                    connect(handler, host, port, maxRetry);
                }, pow * 1000, TimeUnit.MILLISECONDS);
            }

        });
    }
}
