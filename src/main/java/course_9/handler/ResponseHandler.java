package course_9.handler;

import course_9.packet.LoginPacket;
import course_9.packet.PacketCodec;
import course_9.packet.ResponsePacket;
import course_9.packet.UserMessagePacket;
import course_9.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.java.Log;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author peter
 * date: 2019-10-30 14:50
 **/
@Log
public class ResponseHandler extends SimpleChannelInboundHandler<ResponsePacket> {

    private static final String LONGIN = "LONGIN";
    public static final AttributeKey<Object> LOGIN_KEY = AttributeKey.valueOf(LONGIN);

    private int index = 0;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponsePacket msg) throws Exception {


        if (msg.successful()) {
            index++;
            System.out.println("服务器响应--> " + msg.getData());
            new Thread(() -> {
                ThreadLocal<Integer> index = new ThreadLocal<>();
                index.set(1);

                System.out.println("是否要发送消息？");
                System.out.println("1、是  2、否");

                Scanner scanner = new Scanner(System.in);

                String num = scanner.nextLine();

                switch (Integer.parseInt(num)) {
                    case 1:
                        break;
                    case 2:
                        return;
                }

                ThreadLocal<Scanner> threadLocal = ThreadLocal.withInitial(() -> scanner);


                System.out.println("请输入要发送的用户id：");

                String toUserId = threadLocal.get().nextLine();

                System.out.println("请输入要发送的消息：");

                String message = threadLocal.get().nextLine();


                if (Objects.equals("exist", message) || Objects.equals("exist", toUserId)) {
                    log.info("退出程序。。");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ctx.channel().eventLoop().shutdownGracefully();
                    return;
                }

                UserMessagePacket userMessagePacket = new UserMessagePacket();
                userMessagePacket.setMessage(message);
                userMessagePacket.setToUserId(Long.parseLong(toUserId));
                Channel channel = ctx.channel();

                channel.writeAndFlush(userMessagePacket);


            }).start();

        } else {


            System.out.println(msg.errMsg);
            if (index == 0)
                ResponseHandler.login(ctx.channel());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }

    private void login(ChannelHandlerContext ctx) {
        login(ctx.channel());
    }

    public static void login(Channel channel) {
        System.out.println("请输入用户名:");
        Scanner scanner = new Scanner(System.in);

        String user = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();

        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUsername(user);
        loginPacket.setPassword(password);
        PacketCodec codec = new PacketCodec(new JsonSerizalizer());
        ByteBuf encode = codec.encode(channel.alloc().DEFAULT.ioBuffer(), loginPacket);
        channel.writeAndFlush(encode);
    }
}
