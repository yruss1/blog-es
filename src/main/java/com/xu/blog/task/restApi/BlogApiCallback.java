package com.xu.blog.task.restApi;

public interface BlogApiCallback<T> {

    void onResponse(T response);

    default void onFailure(Throwable cause) {}

}
