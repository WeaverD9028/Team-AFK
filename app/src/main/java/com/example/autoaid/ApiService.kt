package com.example.autoaid


import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun getPosts() :retrofit2.Call<MutableList<PostModel>>
}