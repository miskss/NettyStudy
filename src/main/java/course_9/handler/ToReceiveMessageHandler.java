package course_9.handler;

import course_9.packet.ToReceiveMessagePacket;
import course_9.packet.UserMessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

/**
 * @author peter
 * date: 2019-10-31 13:41
 **/

public class ToReceiveMessageHandler extends SimpleChannelInboundHandler<ToReceiveMessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ToReceiveMessagePacket msg) throws Exception {

        System.out.println("收到来自用户:【" + msg.getUsername() + "】的消息。消息内容：");
        System.out.println(msg.getMessage());

        System.out.println("是否回复?");
        System.out.println("1、回复.     2、忽略.    3、退出");

        Scanner scanner = new Scanner(System.in);

        String num = scanner.nextLine();

        switch (Integer.parseInt(num)) {
            case 1:
                System.out.println("请输入回复的内容：");
                String line = scanner.nextLine();

                UserMessagePacket message = new UserMessagePacket();

                message.setToUserId(msg.getFromUserId());
                message.setMessage(line);
                ctx.channel().writeAndFlush(message);
                break;

            case 2:
                break;

            case 3:
                System.out.println("正在退出系统。。。");
                ctx.channel().eventLoop().shutdownGracefully();
                break;
        }

    }
}
