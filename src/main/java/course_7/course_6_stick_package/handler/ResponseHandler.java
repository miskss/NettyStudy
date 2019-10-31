package course_7.course_6_stick_package.handler;

import course_7.course_6_stick_package.packet.LoginPacket;
import course_7.course_6_stick_package.packet.PacketCodec;
import course_7.course_6_stick_package.packet.ResponsePacket;
import course_7.course_6_stick_package.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.Scanner;

/**
 * @author peter
 * date: 2019-10-30 14:50
 **/
public class ResponseHandler extends SimpleChannelInboundHandler<ResponsePacket> {

    private static final String LONGIN = "LONGIN";
    public static final AttributeKey<Object> LOGIN_KEY = AttributeKey.valueOf(LONGIN);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponsePacket msg) throws Exception {


        if (msg.successful()) {
            System.out.println(msg.getData());
            //登录状态
            ctx.channel().attr(LOGIN_KEY).set(1);
        } else {
            System.out.println(msg.errMsg);
            login(ctx);
        }
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
