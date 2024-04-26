package com.xu.blog.server.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 11582
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends Command {

    private Integer type;

    private String target;

    private String content;
}
