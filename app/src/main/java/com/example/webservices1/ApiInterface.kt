package com.example.webservices1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getData(

            //to get parameter
        @Query("id")
        postId: Int
    ): Call<List<DataModel>>
}