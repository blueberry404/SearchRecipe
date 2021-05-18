package com.anum.gtl_assignment.di

import com.anum.gtl_assignment.api.RecipeService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        var okHttpClient = OkHttpClient()
        return okHttpClient.newBuilder().addInterceptor(loggingInterceptor).build()
    }

    @Provides fun getRecipeService(retrofit: Retrofit) : RecipeService =
        retrofit.create(RecipeService::class.java)

}