package com.xu.blog.task.restApi;

import retrofit2.Call;
import retrofit2.http.*;

import static com.xu.blog.config.ApiConfig.AUTHORIZATION;

public interface BlogApiService {

    @FormUrlEncoded
    @POST("/token")
    Call<Object> getAccessToken(@Field("grant_type") String grantType);

    @GET("/api/KbArticles")
    Call<Object> getArticles(@Query("pageIndex") String pageIndex,
                             @Query("pageSize") String pageSize,
                             @Header(AUTHORIZATION) String accessToken);


    @GET("/api/kbarticles/{id}/body")
    Call<Object> getArticleById(@Path("id") String id,
                            @Header(AUTHORIZATION) String accessToken);

}
