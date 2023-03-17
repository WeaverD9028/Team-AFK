package com.example.autoaid


import retrofit2.http.GET
import retrofit2.http.Headers

//Partner Token: db6ef5ce8fd14fcd808057ae6f4c001e
// Auth key: ZjhjODkwN2MtOWRkMy00ZjkzLWIxM2MtZGIyZTM2NTlhMzFk
// /maint?vin=1GNALDEK9FZ108495&mileage=51000
interface ApiService {
    /*
    @Headers(value = ["content-type: application/json",
       "Authorization:Basic ZjhjODkwN2MtOWRkMy00ZjkzLWIxM2MtZGIyZTM2NTlhMzFk",
       "partner-token: db6ef5ce8fd14fcd808057ae6f4c001e"])
    */
    @GET("/posts")
    fun getPosts() :retrofit2.Call<MutableList<PostModel>>
}