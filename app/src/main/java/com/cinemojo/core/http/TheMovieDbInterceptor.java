package com.cinemojo.core.http;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TheMovieDbInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (StringUtils.isEmpty(request.url().queryParameter("api_key"))) {
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", "4bbbfeaa56287e045385e892563e7168")
                    .build();
            request = request.newBuilder().url(url).build();
        }
        return chain.proceed(request);
    }
}
