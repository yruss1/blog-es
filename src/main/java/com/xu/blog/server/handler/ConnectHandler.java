package com.xu.blog.server.handler;

import com.alibaba.fastjson2.JSON;
import com.xu.blog.server.IMServer;
import com.xu.blog.server.domain.Result;
import com.xu.blog.server.domain.pojo.Command;
import io.netty.channel.ChannelHandlerContext;

/**
 * 连接请求的处理器
 *
 * @author 11582*/
public class ConnectHandler {

    public static void execute(ChannelHandlerContext channelHandlerContext, Command command) {
        //判断用户是否已上线
        if (IMServer.USERS.containsKey(command.getNickname())) {
            channelHandlerContext.channel().writeAndFlush(Result.fail("该用户已上线，请换个昵称再试~"));
            //断开连接
            channelHandlerContext.channel().disconnect();
            return;
        }
        IMServer.USERS.put(command.getNickname(), channelHandlerContext.channel());
        channelHandlerContext.channel().writeAndFlush(Result.success("与服务端建立连接成功"));
        //返回群聊的人
        channelHandlerContext.channel().writeAndFlush(Result.success(JSON.toJSONString(IMServer.USERS.keySet())));
    }
}
