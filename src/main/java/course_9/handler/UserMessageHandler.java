package course_9.handler;

import course_9.context.SessionContext;
import course_9.context.SessionContextManager;
import course_9.context.User;
import course_9.context.UserPool;
import course_9.packet.MessageResponse;
import course_9.packet.ToReceiveMessagePacket;
import course_9.packet.UserMessagePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author peter
 * date: 2019-10-31 11:29
 **/
public class UserMessageHandler extends SimpleChannelInboundHandler<UserMessagePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserMessagePacket msg) throws Exception {
        Long toUserId = msg.getToUserId();

        User user = UserPool.getOne(toUserId);

        Channel channel = ctx.channel();
        if (user == null) {
            channel.writeAndFlush(new MessageResponse("未找到用户：" + toUserId));
            return;
        }

        SessionContext one = SessionContextManager.getOne(toUserId);

        if (one == null) {


            channel.writeAndFlush(new MessageResponse("用户：" + user.getUsername() + "不在线"));

        } else {

            channel.writeAndFlush(new MessageResponse("消息发送成功"));

            ToReceiveMessagePacket packet = new ToReceiveMessagePacket();
            User user1 = SessionContextManager.fromChannel(channel);
            if (user1 != null) {
                packet.setUsername(user1.getUsername());
                packet.setFromUserId(user1.getUserId());
            }
            packet.setMessage(msg.getMessage());

            one.getChannel().writeAndFlush(packet);
        }


    }
}
