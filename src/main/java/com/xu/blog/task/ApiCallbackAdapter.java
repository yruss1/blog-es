package com.xu.blog.task;

import com.xu.blog.common.exception.ApiError;
import com.xu.blog.common.exception.ApiException;
import com.xu.blog.task.restApi.BlogApiCallback;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

import static com.xu.blog.task.ApiServiceGenerator.getApiError;

/**
 * @author 11582
 */
public class ApiCallbackAdapter<T> implements Callback<T> {

    private final BlogApiCallback<T> callback;

    public ApiCallbackAdapter(BlogApiCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            if (response.code() == 504) {
                // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
                // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
                return;
            }
            try {
                ApiError apiError = getApiError(response);
                onFailure(call, new ApiException(apiError));
            } catch (IOException e) {
                onFailure(call, new ApiException(e));
            }
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable throwable) {
        if (throwable instanceof ApiException) {
            callback.onFailure(throwable);
        } else {
            callback.onFailure(new ApiException(throwable));
        }
    }
}
