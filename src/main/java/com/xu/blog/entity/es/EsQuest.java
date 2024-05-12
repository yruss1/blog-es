package com.xu.blog.entity.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 11582
 */
@Data
@Document(indexName = "quest", type = "doc", useServerConfiguration = true, createIndex = false)
public class EsQuest {

    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_smart_word")
    @JsonProperty("send_message")
    private String sendMessage;
    @Field(type = FieldType.Text)
    @JsonProperty("sender_name")
    private String senderName;
    @Field(type = FieldType.Text, analyzer = "ik_smart_word")
    @JsonProperty("reply_message")
    private String replyMessage;
    @Field(type = FieldType.Text)
    @JsonProperty("receiver_name")
    private String receiverName;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @JsonProperty("send_time")
    private String  sendTime;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @JsonProperty("reply_time")
    private String replyTime;

}
