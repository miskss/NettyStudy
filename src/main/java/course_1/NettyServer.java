package course_1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author peter
 * date: 2019-10-21 13:47
 **/
class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //boss线程 用于监听 连接的接入
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程 用于处理业务
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建启动引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)//设置工作组
                .channel(NioServerSocketChannel.class)//设置 IO 模型
                .handler(new ChannelInitializer<NioServerSocketChannel>() {//指定在服务端启动过程中的一些逻辑
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务端启动中");
                    }

                })
                .option(ChannelOption.SO_BACKLOG, 1024)//给服务端channel设置一些属性,表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {//定义后续每条连接的数据读写，业务处理逻辑
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("客户端收到消息。。。");
                    }
                });//绑定监听端口

        bind(serverBootstrap, 8080);
    }

    private static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) throws InterruptedException {
        return serverBootstrap.bind(port)
                .addListener(future -> {
                    if (future.isSuccess()) {

                        System.out.println("端口【" + port + "】绑定成功");

                    } else {
                        System.err.println("端口【" + port + "】绑定失败!");
                        bind(serverBootstrap, port + 1);
                    }
                });
    }
}
