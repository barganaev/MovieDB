package com.example.movie_application.data.repository

import com.example.movie_application.data.models.AccountData
import com.example.movie_application.data.network.MovieApi
import com.example.movie_application.domain.repository.UserRepository

/*
import com.example.movieapp.data.models.AccountData
import com.example.movieapp.data.network.MovieApi
*/

import com.google.gson.JsonObject
import retrofit2.Response

class UserRepositoryImpl(
    private val movieApi: MovieApi
): UserRepository{
    private var requestToken: String? = null

    override suspend fun createToken(): Response<JsonObject> {
        val requestTokenResponse = movieApi.createRequestToken().await()
        requestToken = requestTokenResponse
            .body()
            ?.getAsJsonPrimitive("request_token")
            ?.asString
        return requestTokenResponse
    }

    override suspend fun login(username: String, password: String) : Boolean {
        val body = JsonObject().apply {
            addProperty("username", username)
            addProperty("password", password)
            addProperty("request_token", requestToken)
        }
        val loginResponse = movieApi.login(body).await()
        return loginResponse.body()?.getAsJsonPrimitive("success")?.asBoolean ?: false
    }

    override suspend fun createSession(): Response<JsonObject> {
        val body = JsonObject().apply {
            addProperty("request_token", requestToken)
        }
        return movieApi.createSession(body).await()
    }

    //Account
    override suspend fun getAccountDetails(sessionId: String): AccountData? =
        movieApi.getAccountId(sessionId).await().body()
}