package com.xu.blog.server.handler;

import com.alibaba.fastjson2.JSON;
import com.xu.blog.server.IMServer;
import com.xu.blog.server.domain.Result;
import com.xu.blog.server.domain.enums.MessageType;
import com.xu.blog.server.domain.pojo.ChatMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.StringUtil;


public class ChatHandler {
    public static void execute(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {
        try {
            ChatMessage chat = JSON.parseObject(textWebSocketFrame.text(), ChatMessage.class);
            switch (MessageType.match(chat.getType())) {
                case PRIVATE:
                    if (StringUtil.isNullOrEmpty(chat.getTarget())) {
                        channelHandlerContext.channel().writeAndFlush(Result.fail("消息发送失败，消息发送前请指定接收对象"));
                        return;
                    }
                    Channel channel = IMServer.USERS.get(chat.getTarget());
                    if (null == channel || !channel.isActive()) {
                        channelHandlerContext.channel().writeAndFlush(Result.fail("消息发送失败，对方" + chat.getTarget() + "不在线"));
                    } else {
                        channel.writeAndFlush(Result.success("私聊消息(" + chat.getNickname() + ")", chat.getContent()));
                    }
                    break;
                case GROUP:
                    IMServer.GROUP.writeAndFlush(Result.success("群聊消息(" + chat.getNickname() + ")", chat.getContent()));
                    break;
                default:
                    channelHandlerContext.channel().writeAndFlush(Result.fail("不支持消息类型"));
            }
        } catch (Exception e) {

        }
    }
}
