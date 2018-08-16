package com.cinemojo.core.repository;

import com.cinemojo.core.model.PagingResponse;
import com.cinemojo.core.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRepository {

    @GET("movie/popular")
    Call<PagingResponse<MovieResponse>> findPopularMovies(@Query("page") int page);

    @GET("movie/now_playing")
    Call<PagingResponse<MovieResponse>> findNowPlayingMovies(@Query("page") int page);

    @GET("movie/upcoming")
    Call<PagingResponse<MovieResponse>> findUpcomingMovies(@Query("page") int page);


}
