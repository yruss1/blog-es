package com.xu.blog.entity.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author 11582
 */
@Data
@Document(indexName = "blog1", type = "doc", useServerConfiguration = true, createIndex = false)
public class EsBlog implements Serializable {

    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_smart_word")
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_smart_word")
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_smart_word", fielddata = true)
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @JsonProperty("create_time")
    private String createTime;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @JsonProperty("update_time")
    private String  updateTime;
    @Field(type = FieldType.Text)
    @JsonProperty("view_count")
    private String viewCount;
    @Field(type = FieldType.Text)
    @JsonProperty("dig_count")
    private String digCount;
}