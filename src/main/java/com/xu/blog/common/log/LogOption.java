package com.xu.blog.common.log;

import lombok.Getter;

@Getter
public enum LogOption {
    SLF4J("slf4j"),
    OKHTTP3("okhttp3"),
    UNKNOWN("unknown"),
    CUSTOMIZE("customize");

    private final String logOptionType;

    LogOption(String logOptionType) {
        this.logOptionType = logOptionType;
    }
}
