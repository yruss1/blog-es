package com.xu.blog.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor@NoArgsConstructor
public class RawBlog implements Serializable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Summary")
    private String summary;
    @SerializedName("Author")
    private String author;
    @SerializedName("ViewCount")
    private String viewCount;
    @SerializedName("DiggCount")
    private String diggCount;
    @SerializedName("DateAdded")
    private String createTime;

}