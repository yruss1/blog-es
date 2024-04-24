package com.xu.blog.task;

import com.xu.blog.task.restApi.BlogRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.xu.blog.config.ApiConfig.BLOG_URL;

public class RestApiClientFactory {

    private final String clientId;
    private final String clientSecret;
    private final String baseUrl;
    private final Boolean debugMode;
    private final static Logger logger = LoggerFactory.getLogger(RestApiClientFactory.class);

    private RestApiClientFactory(String clientId, String clientSecret, String baseUrl, Boolean debugMode){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.debugMode = debugMode;
    }

    public static RestApiClientFactory newInstance(String clientId, String clientSecret, String baseUrl){
        return new RestApiClientFactory(clientId, clientSecret, baseUrl, false);
    }

    public static RestApiClientFactory newInstance(String clientId, String clientSecret){
        return new RestApiClientFactory(clientId, clientSecret, BLOG_URL, false);
    }

    public BlogRestClient newBlogRestClient(){
        return new BlogRestClientImpl(clientId, clientSecret, baseUrl, debugMode);
    }
}
