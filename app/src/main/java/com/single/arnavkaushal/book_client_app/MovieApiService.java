package com.single.arnavkaushal.book_client_app;

import com.single.arnavkaushal.book_client_app.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService
{
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchMovieNames(@Query("api_key") String apiKey, @Query("query") String queryTerm);
}
