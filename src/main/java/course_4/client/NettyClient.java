package course_4.client;

import course_4.packet.MessagePacket;
import course_4.packet.PacketCodec;
import course_4.serializer.JsonSerizalizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import lombok.extern.java.Log;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author peter
 * date: 2019-10-29 17:20
 **/
@Log
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        ChannelFuture connect = new Bootstrap()
                .group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientMsgHandler());
                    }
                }).connect("192.168.1.222", 8000);
        connect.addListener(future -> {
            if (future.isSuccess()) {
                new Thread(() -> {

                    ThreadLocal<Integer> index = new ThreadLocal<>();
                    index.set(1);

                    Scanner scanner = new Scanner(System.in);

                    ThreadLocal<Scanner> threadLocal = ThreadLocal.withInitial(() -> scanner);

                    while (!Thread.interrupted()) {

                        Attribute<Object> attr = connect.channel().attr(ClientMsgHandler.LOGIN_KEY);

                        if (attr.get() != null) {
                            if (index.get() == 1) {
                                log.info("可以发送消息了");
                                index.set(index.get() + 1);
                            }
                            System.out.println("请输入要发送的内容：");
                            String line = threadLocal.get().nextLine();
                            if (Objects.equals("exist", line)) {
                                log.info("退出程序。。");
                                break;
                            }

                            PacketCodec codec = new PacketCodec(new JsonSerizalizer());
                            MessagePacket messagePacket = new MessagePacket();
                            messagePacket.setMsg(line);
                            Channel channel = connect.channel();
                            ByteBuf encode = codec.encode(channel.alloc().DEFAULT.ioBuffer(), messagePacket);

                            channel.writeAndFlush(encode);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    eventExecutors.shutdownGracefully();

                }).start();
            }
        });

        ClientMsgHandler.login(connect.channel());


    }
}
