package client.handler;

import client.entity.Message;
import client.io.Object2Array;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private Message message;

    /**
     * 接收server端的消息，并打印出来
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        result.release();
    }

    /**
     * 连接成功后，向server发送消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        byte[] bytes = Object2Array.objectToByteArray(message);

        ByteBuf encoded = ctx.alloc().buffer(bytes.length);
        encoded.writeBytes(bytes);
        ctx.write(encoded);
        ctx.flush();
    }
}