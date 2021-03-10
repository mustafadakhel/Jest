package com.martin.jokes.api

import com.martin.jokes.models.Joke
import retrofit2.http.GET

interface JokesApi {
    @GET("random_ten")
    suspend fun getTenRandomJokes(): MutableList<Joke>
}