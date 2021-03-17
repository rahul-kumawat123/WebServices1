package com.example.webservices1

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP

object ApiClient {

    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val getClient : ApiInterface
    get() {

        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit =Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

        return retrofit.create(ApiInterface::class.java)

    }

}