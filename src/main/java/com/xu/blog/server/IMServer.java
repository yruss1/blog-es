package com.xu.blog.server;

import com.xu.blog.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 11582
 */
public class IMServer {

    public static final Map<String, Channel> USERS = new ConcurrentHashMap<>(1024);

    public static final ChannelGroup GROUP=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void start() {
        //创建线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        //创建工作线程
        EventLoopGroup worker = new NioEventLoopGroup();

        //绑定端口
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //添加http编码解码器
                        pipeline.addLast(new HttpServerCodec())
                                //支持大数据流
                                .addLast(new ChunkedWriteHandler())
                                //对http消息做聚合操作，会产生FullHttpRequest、FullHttpResponse
                                .addLast(new HttpObjectAggregator(1024 * 64))
                                //websocket支持
                                .addLast(new WebSocketServerProtocolHandler("/")) //websocket的根路径
                                .addLast(new WebSocketHandler());
                    }
                });

        //绑定Netty的启动端口
        ChannelFuture future = bootstrap.bind(8888);
    }
}
