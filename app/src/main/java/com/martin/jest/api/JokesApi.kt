package com.martin.jest.api

import com.martin.jest.models.Joke
import retrofit2.http.GET

interface JokesApi {
    @GET("random_ten")
    suspend fun getTenRandomJokes(): MutableList<Joke>
}