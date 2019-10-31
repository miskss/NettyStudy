package course_7.stick_package;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @author peter
 * date: 2019-10-22 10:56
 **/
class DuplexServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                //添加一个固定长度的拆包器
                                .addLast(new FixedLengthFrameDecoder(15))
                                .addLast(new DuplexServerHandler());
                    }
                }).bind(8000).addListener(future -> {

            if (future.isSuccess()) {
                System.out.println("服务启动成功，端口【" + 8000 + "】");
            } else {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        });
    }

}
