package com.eblee.pokeguide.di

import com.eblee.pokeguide.BuildConfig
import com.eblee.pokeguide.data.api.PokemonApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModules = module {

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(client)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    single { provideRetrofit(get()) }
    single { provideOkHttpClient() }

    fun pokemonApiService(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    single { pokemonApiService(get()) }

}