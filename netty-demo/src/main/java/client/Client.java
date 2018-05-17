package client;

import client.entity.ClientInfo;
import client.entity.Message;
import client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Client {

    public void connect(Message message, String host) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler(message));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, message.getClientInfo().getPort()).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {

        List<Message> messageList = new ArrayList<Message>(3);

        ClientInfo clientInfo1 = new ClientInfo();
        clientInfo1.setPort(8000);
        Message message1 = new Message();
        message1.setClientInfo(clientInfo1);
        message1.setMessageId(0L);
        message1.setMessage("Client1");
        messageList.add(message1);



        final Client client = new Client();
        messageList.parallelStream().forEach( message -> {
            try {
                client.connect(message, "lcoalhost");
            } catch (Exception e) {
                log.error("{}", e);
            }
        });
    }
}
