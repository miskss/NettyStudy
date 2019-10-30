package course_4.client;

import course_4.packet.LoginPacket;
import course_4.packet.Packet;
import course_4.packet.PacketCodec;
import course_4.packet.ResponsePacket;
import course_4.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.util.Scanner;

/**
 * @author peter
 * date: 2019-10-29 17:44
 **/
public class ClientMsgHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final String LONGIN = "LONGIN";
    static final AttributeKey<Object> LOGIN_KEY = AttributeKey.valueOf(LONGIN);
    private PacketCodec packetCodec = new PacketCodec(new JsonSerizalizer());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Packet decode = packetCodec.decode(msg);
        System.out.println(decode);

        if (decode instanceof ResponsePacket) {

            ResponsePacket responsePacket = (ResponsePacket) decode;

            if (responsePacket.successful()) {
                System.out.println(responsePacket.getData());
                //登录状态
                ctx.channel().attr(LOGIN_KEY).set(1);
            } else {
                System.out.println(responsePacket.errMsg);
                login(ctx);
            }
        }

    }

    private void login(ChannelHandlerContext ctx) {
        login(ctx.channel());
    }

    static void login(Channel channel) {
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
