package com.xu.blog.task.restApi;

/**
 * @author 11582
 */
public interface BlogApiCallback<T> {

    void onResponse(T response);

    default void onFailure(Throwable cause) {}

}
