package com.xu.blog.common.log;

import com.xu.blog.task.ApiServiceGenerator;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Logging Interceptor
 * @author 11582
 */
public class Slf4jLoggingInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceGenerator.class);

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Log request headers using SLF4J
        logHeaders(request.headers(), "--- request header ---");

        Response response = chain.proceed(request);

        // Log response headers using SLF4J
        logHeaders(response.headers(), "--- response header ---");

        return response;
    }

    private void logHeaders(Headers headers, String headerTitle) {
        LOGGER.info(headerTitle);
        for (String name : headers.names()) {
            LOGGER.info("{}: {}", name, headers.get(name));
        }
        LOGGER.info("-----------------------");
    }


    public static void HandleLoggingInterceptor(OkHttpClient.Builder clientBuilder) {
        LOGGER.info("Debug Mode Activated, Trace Request in body level");
        clientBuilder.addInterceptor(new Slf4jLoggingInterceptor());

    }
}
