package course_4.server;

import course_4.packet.*;
import course_4.serializer.JsonSerizalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

import java.util.Objects;

/**
 * @author peter
 * date: 2019-10-29 17:10
 **/
@Log
public class PacketChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private PacketCodec packetCodec = new PacketCodec(new JsonSerizalizer());


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        Packet decode = packetCodec.decode(msg);

        System.out.println(decode);
        ResponsePacket responsePacket = null;

        if (decode instanceof LoginPacket) {
            responsePacket = handleLogin((LoginPacket) decode);
        } else if (decode instanceof MessagePacket) {
            responsePacket = handleMessage((MessagePacket) decode);
        }
        ctx.channel().writeAndFlush(packetCodec.encode(ctx.alloc().DEFAULT.ioBuffer(), responsePacket));

    }

    private ResponsePacket handleMessage(MessagePacket decode) {

        String msg = decode.getMsg();
        log.info("收到Message消息: " + msg);

        return ResponsePacket.success("服务器回应:" + msg);


    }

    public ResponsePacket handleLogin(LoginPacket packet) {

        if (Objects.equals(packet.getPassword(), "11") && Objects.equals(packet.getUsername(), "22")) {
            return ResponsePacket.success(1);
        } else {
            return ResponsePacket.fail("账号或密码不正确");
        }


    }

}
