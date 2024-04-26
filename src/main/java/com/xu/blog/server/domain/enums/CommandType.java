package com.xu.blog.server.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聊天的命令枚举
 *
 * @author 11582*/
@Getter
@AllArgsConstructor
public enum CommandType {
    /**
     * 状态码
     */
    CONNECTION(1001),
    CHAT(1002),
    JOIN_GROUP(1003),
    ERROR(-1);

    private final Integer code;

    public static CommandType match(Integer code) {
        for (CommandType value : CommandType.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return ERROR;
    }
}
