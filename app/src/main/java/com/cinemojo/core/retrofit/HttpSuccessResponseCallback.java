package com.cinemojo.core.retrofit;

public interface HttpSuccessResponseCallback<T> {
    void onSuccessResponse(T responseBody);
}
