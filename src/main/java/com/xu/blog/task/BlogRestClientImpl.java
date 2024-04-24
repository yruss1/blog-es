package com.xu.blog.task;

import com.xu.blog.task.restApi.BlogApiCallback;
import com.xu.blog.task.restApi.BlogApiService;
import com.xu.blog.task.restApi.BlogRestClient;

import static com.xu.blog.config.ApiConfig.GRANT_TYPE;
import static com.xu.blog.task.ApiServiceGenerator.createService;

public class BlogRestClientImpl implements BlogRestClient {

    private final BlogApiService blogApiService;

    public BlogRestClientImpl(String clientId, String clientSecret, String baseUrl, Boolean debugMode){
        blogApiService = createService(
                BlogApiService.class,
                clientId,
                clientSecret,
                baseUrl,
                debugMode
        );
    }

    @Override
    public void getAccessToken(BlogApiCallback<Object> callback) {
        blogApiService.getAccessToken(GRANT_TYPE)
                .enqueue(new ApiCallbackAdapter<>(callback));
    }

    @Override
    public void getArticles(String pageIndex, String pageSize, String accessToken, BlogApiCallback<Object> callback) {
        blogApiService.getArticles(
                pageIndex,
                pageSize,
                accessToken
        ).enqueue(new ApiCallbackAdapter<>(callback));
    }

    @Override
    public void getArticleById(String id, String accessToken, BlogApiCallback<Object> callback) {
        blogApiService.getArticleById(
                id,
                accessToken
        ).enqueue(new ApiCallbackAdapter<>(callback));
    }
}
