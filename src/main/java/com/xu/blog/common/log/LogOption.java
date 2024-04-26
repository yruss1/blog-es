package com.xu.blog.common.log;

import lombok.Getter;

/**
 * @author 11582
 */

@Getter
public enum LogOption {
    /**
     *  slf4j、okhttp3、unknown、customize
     */
    SLF4J("slf4j"),
    OKHTTP3("okhttp3"),
    UNKNOWN("unknown"),
    CUSTOMIZE("customize");

    /**
     *  日志选择
     */
    private final String logOptionType;

    LogOption(String logOptionType) {
        this.logOptionType = logOptionType;
    }
}
