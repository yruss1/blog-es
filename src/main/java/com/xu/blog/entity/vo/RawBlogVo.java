package com.xu.blog.entity.vo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 11582
 */
@Data
@AllArgsConstructor@NoArgsConstructor
public class RawBlogVo implements Serializable {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Summary")
    private String summary;
    @SerializedName("Author")
    private String author;
    @SerializedName("ViewCount")
    private Integer viewCount;
    @SerializedName("DiggCount")
    private Integer diggCount;
    @SerializedName("DateAdded")
    private String createTime;

}
