package com.example.autoaid

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Basic ZjhjODkwN2MtOWRkMy00ZjkzLWIxM2MtZGIyZTM2NTlhMzFk")
            .addHeader("partner-token", "db6ef5ce8fd14fcd808057ae6f4c001e")
            .build()
        return chain.proceed(request)
    }
}