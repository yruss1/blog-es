package com.xu.blog.server.handler;

import com.alibaba.fastjson2.JSON;
import com.xu.blog.server.domain.Result;
import com.xu.blog.server.domain.enums.CommandType;
import com.xu.blog.server.domain.pojo.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;


/**
 * @author 11582
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {//通过TextWebSocketFrame作为消息承载体

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        try {
            //将json文本解析为指令对象
            Command command = JSON.parseObject(textWebSocketFrame.text(), Command.class);
            switch (CommandType.match(command.getCode())) {
                case CONNECTION:
                    ConnectHandler.execute(channelHandlerContext, command);
                    break;
                case CHAT:
                    ChatHandler.execute(channelHandlerContext, textWebSocketFrame);
                    break;
                case JOIN_GROUP:
                    JoinGroupHandler.execute(channelHandlerContext);
                    break;
                default:
                    channelHandlerContext.channel().writeAndFlush(Result.fail("不支持CODE"));
            }
        } catch (Exception e) {
            channelHandlerContext.channel().writeAndFlush(Result.fail("错误消息：" + e.getMessage()));
        }

    }
}
