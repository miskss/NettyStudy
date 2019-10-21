package course_1;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author peter
 * date: 2019-10-21 13:47
 **/
public class NettyServer {

    public static void main(String[] args) {
        //boss线程 用于监听 连接的接入
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程 用于处理业务
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    }
}
