package com.martin.jokes.di.modules

import com.martin.jokes.BuildConfig
import com.martin.jokes.api.JokesApi
import com.martin.jokes.di.annotations.Jokes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class WebServicesModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            okHttpBuilder.addInterceptor(loggingInterceptor)

        val client = okHttpBuilder.build()
        client.dispatcher.maxRequests = 2

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