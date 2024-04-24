package com.xu.blog.task;

import com.xu.blog.exception.ApiError;
import com.xu.blog.exception.ApiException;
import com.xu.blog.task.security.AuthenticationInterceptor;
import lombok.Getter;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import static com.xu.blog.log.Slf4jLoggingInterceptor.HandleLoggingInterceptor;

public class ApiServiceGenerator {

    @Getter
    private static final OkHttpClient sharedClient;
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create();

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .pingInterval(20, TimeUnit.SECONDS)
                .build();
    }


    @SuppressWarnings("unchecked")
    @Nullable
    private static final Converter<ResponseBody, ApiError> errorBodyConverter =
            (Converter<ResponseBody, ApiError>) converterFactory.responseBodyConverter(
                    ApiError.class, new Annotation[0], null);

    public static <S> S createService(Class<S> serviceClass, String baseUrl, boolean debugMode) {
        return createService(serviceClass, null, null, baseUrl, debugMode);
    }

    public static <S> S createService(Class<S> serviceClass, String apiKey, String secret, String baseUrl, boolean debugMode) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory);
        OkHttpClient.Builder clientBuilder = sharedClient.newBuilder();
        if (!StringUtils.isEmpty(apiKey) && !StringUtils.isEmpty(secret)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret);
            clientBuilder.addInterceptor(interceptor);
        }
        if (debugMode) {
            HandleLoggingInterceptor(clientBuilder);
        }
        retrofitBuilder.client(clientBuilder.build());
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> Object executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                ApiError apiError = getApiError(response);
                throw new ApiException(apiError);
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    public static ApiError getApiError(Response<?> response) throws IOException, ApiException {
        ResponseBody errorBody = response.errorBody();
        if (errorBody != null && errorBodyConverter != null) {
            return errorBodyConverter.convert(errorBody);
        }
        // Handle the case where there is no error converter or error body.
        throw new ApiException("Response error body was null or couldn't be converted.");
    }
}
