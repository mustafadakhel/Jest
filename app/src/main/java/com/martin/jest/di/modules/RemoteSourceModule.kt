package com.martin.jest.di.modules

import com.martin.jest.BuildConfig
import com.martin.jest.api.JokesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("unused")
@InstallIn(SingletonComponent::class)
@Module
class RemoteSourceModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)

        val client = okHttpBuilder.build()
        client.dispatcher().maxRequests = 2

        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

        return builder
            .baseUrl(BuildConfig.HOST_URL)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideApiServices(retrofit: Retrofit): JokesApi {
        return retrofit.create(JokesApi::class.java)
    }
}