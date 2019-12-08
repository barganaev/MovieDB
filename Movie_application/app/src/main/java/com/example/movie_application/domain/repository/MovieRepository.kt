package com.example.movie_application.domain.repository

import com.example.movie_application.data.models.MovieData
import com.example.movie_application.data.models.MovieResponseData

interface MovieRepository {

    // Movie
    suspend fun getPopularMovies(page: Int) : MovieResponseData?

    suspend fun getMovieById(movieId: Int): MovieData?

    suspend fun getFavoriteMovies(accountId: Int, sessionId: String, page: Int): MovieResponseData?

    suspend fun rateMovie(movieId: Int, accountId: Int, sessionId: String, favorite:Boolean): Int?

}