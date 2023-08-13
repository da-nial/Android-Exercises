package com.example.weather.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "8b4ff5d5f6dfc664ab85a2edb01008ba"

val loggingInterceptor = HttpLoggingInterceptor()
val httpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()


interface WeatherApiService {
    @GET("forecast")
    suspend fun get5DaysData(
        @Query("lat") lat: String = "51.5073219",
        @Query("lon") lon: String = "-0.1276474",
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherData?
}

object WeatherAPI {
    val retrofitService: WeatherApiService by lazy {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        retrofit.create(WeatherApiService::class.java)
    }
}

