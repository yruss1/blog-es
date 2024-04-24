package com.xu.blog.task.security;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import static com.xu.blog.config.ApiConfig.CLIENT_ID;
import static com.xu.blog.config.ApiConfig.CLIENT_SECRET;


/**
 * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String clientId;

    private final String secret;

    public AuthenticationInterceptor(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();
        if ("POST".equals(original.method()) && original.body() instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            FormBody formBody = (FormBody) original.body();
            for (int i = 0; i < formBody.size(); i++) {
                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
            }
            formBody = bodyBuilder
                    .addEncoded(CLIENT_ID, clientId)
                    .addEncoded(CLIENT_SECRET, secret)
                    .build();
            newRequestBuilder.post(formBody);
        }
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, secret);
    }
}