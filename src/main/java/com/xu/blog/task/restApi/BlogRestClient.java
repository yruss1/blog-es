package com.xu.blog.task.restApi;

/**
 * @author 11582
 */
public interface BlogRestClient {

    /**
     * 获取token
     * @param callback 回调
     */
    void getAccessToken(BlogApiCallback<Object> callback);

    /**
     * 获取文章信息
     * @param pageIndex 页码
     * @param pageSize  容量
     * @param accessToken   token
     * @param callback  回调
     */
    void getArticles(String pageIndex, String pageSize, String accessToken, BlogApiCallback<Object> callback);

    /**
     * 获取文章
     * @param id 文章id
     * @param accessToken token
     * @param callback 回调
     */
    void getArticleById(String id, String accessToken, BlogApiCallback<Object> callback);
}
