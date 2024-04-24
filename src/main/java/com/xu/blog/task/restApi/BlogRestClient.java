package com.xu.blog.task.restApi;

public interface BlogRestClient {

    void getAccessToken(BlogApiCallback<Object> callback);
    void getArticles(String pageIndex, String pageSize, String accessToken, BlogApiCallback<Object> callback);
    void getArticleById(String id, String accessToken, BlogApiCallback<Object> callback);
}
