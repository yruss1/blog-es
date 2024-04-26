package com.xu.blog.server.handler;

import com.xu.blog.server.IMServer;
import com.xu.blog.server.domain.Result;
import io.netty.channel.ChannelHandlerContext;


public class JoinGroupHandler {
    public static void execute(ChannelHandlerContext channelHandlerContext){
        //将Channel添加到ChannelGroup
        IMServer.GROUP.add(channelHandlerContext.channel());
        channelHandlerContext.channel().writeAndFlush(Result.success("加入系统默认群聊成功~"));
    }
}
