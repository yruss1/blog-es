package com.xu.blog.server.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends Command {

    //消息类型
    private Integer type;

    //目标接收对象
    private String target;

    //消息内容
    private String content;
}
