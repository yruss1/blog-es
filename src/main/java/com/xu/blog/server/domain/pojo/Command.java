package com.xu.blog.server.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {

    //连接信息编码
    private Integer code;

    //用户昵称
    private String nickname;
}
