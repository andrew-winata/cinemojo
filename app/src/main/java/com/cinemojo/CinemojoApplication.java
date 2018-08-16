package com.cinemojo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.cinemojo.core.ApiPath;
import com.cinemojo.core.http.TheMovieDbInterceptor;
import com.cinemojo.core.repository.LoginSessionRepository;
import com.cinemojo.core.repository.MovieRepository;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CinemojoApplication extends MultiDexApplication {

    private static CinemojoApplication mInstance;

    private LoginSessionRepository loginSessionRepository;
    private MovieRepository movieRepository;
    private OkHttpClient theMovieDbHttpClient;
    private Retrofit theMovieDbRetrofit;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        StethoInterceptor stethoInterceptor = new StethoInterceptor();
        theMovieDbHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TheMovieDbInterceptor())
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .readTimeout(4000, TimeUnit.MILLISECONDS)
                .build();
        theMovieDbRetrofit = new Retrofit.Builder()
                .baseUrl(ApiPath.TMDB_API_BASE_URL)
                .client(theMovieDbHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
        loginSessionRepository = LoginSessionRepository.getInstance(this);
        movieRepository = theMovieDbRetrofit.create(MovieRepository.class);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static CinemojoApplication getInstance() {
        return mInstance;
    }

    public LoginSessionRepository getLoginSessionRepository() {
        return this.loginSessionRepository;
    }

    public MovieRepository getMovieRepository() {
        return this.movieRepository;
    }
}
