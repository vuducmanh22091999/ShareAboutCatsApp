package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices
import com.example.shareaboutcatsapp.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { providerGson() }
    single { providerRetrofit(get()) }
    single { providerApp(get()) }
    single { providerHttp() }
}

fun providerGson() : Gson = GsonBuilder().create()

fun providerRetrofit(gson: Gson) : Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson)).build()

fun providerApp(retrofit: Retrofit): ShareAboutCatsAppServices = retrofit.create(ShareAboutCatsAppServices::class.java)

fun providerHttp(): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    okHttpBuilder.addInterceptor(logging)
    return okHttpBuilder.build()
}