package com.cinemojo.core.retrofit;

import lombok.Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Builder
public class RetrofitCallback<T> implements Callback<T> {

    private ResponseCompletedCallback responseCompletedCallback;
    private HttpSuccessResponseCallback<T> httpSuccessResponseCallback;
    private HttpErrorResponseCallback httpErrorResponseCallback;
    private ClientErrorCallback clientErrorCallback;


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (responseCompletedCallback != null) {
            responseCompletedCallback.onCompleted();
        }

        if (response.isSuccessful()) {
            T responseBody = response.body();
            if (httpSuccessResponseCallback != null) {
                httpSuccessResponseCallback.onSuccessResponse(responseBody);
            }
        } else if (httpErrorResponseCallback != null) {
            httpErrorResponseCallback.onErrorResponse();
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (clientErrorCallback != null) {
            clientErrorCallback.onClientError(t);
        }
    }
}
