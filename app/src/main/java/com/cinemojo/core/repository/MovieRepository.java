package com.cinemojo.core.repository;

import com.cinemojo.core.model.MoviePagingResponse;
import com.cinemojo.core.model.PopularMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRepository {

    @GET("/movie/popular")
    Call<MoviePagingResponse<PopularMovieResponse>> findPopularMovies(@Query("page") int page);
}
