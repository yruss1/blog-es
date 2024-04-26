package com.xu.blog.task;

import com.xu.blog.task.restApi.BlogRestClient;

import static com.xu.blog.common.config.ApiConfig.BLOG_URL;

/**
 * @author 11582
 */
public class RestApiClientFactory {

    private final String clientId;
    private final String clientSecret;
    private final String baseUrl;
    private final Boolean debugMode;

    private RestApiClientFactory(String clientId, String clientSecret, String baseUrl, Boolean debugMode){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.debugMode = debugMode;
    }

    public static RestApiClientFactory newInstance(String clientId, String clientSecret){
        return new RestApiClientFactory(clientId, clientSecret, BLOG_URL, false);
    }

    public BlogRestClient newBlogRestClient(){
        return new BlogRestClientImpl(clientId, clientSecret, baseUrl, debugMode);
    }
}
