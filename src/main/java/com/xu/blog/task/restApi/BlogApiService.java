package com.xu.blog.task.restApi;

import retrofit2.Call;
import retrofit2.http.*;

import static com.xu.blog.common.config.ApiConfig.AUTHORIZATION;

/**
 * @author 11582
 */
public interface BlogApiService {

    /**
     * 获取token
     * @param grantType 鉴权类型
     * @return 回调
     */
    @FormUrlEncoded
    @POST("/token")
    Call<Object> getAccessToken(@Field("grant_type") String grantType);

    /**
     * 获取文章信息
     * @param pageIndex 页码
     * @param pageSize 容量
     * @param accessToken token
     * @return 回调
     */
    @GET("/api/KbArticles")
    Call<Object> getArticles(@Query("pageIndex") String pageIndex,
                             @Query("pageSize") String pageSize,
                             @Header(AUTHORIZATION) String accessToken);

    /**
     * 获取文章
     * @param id 文章id
     * @param accessToken token
     * @return 回调
     */
    @GET("/api/kbarticles/{id}/body")
    Call<Object> getArticleById(@Path("id") String id,
                            @Header(AUTHORIZATION) String accessToken);

}
