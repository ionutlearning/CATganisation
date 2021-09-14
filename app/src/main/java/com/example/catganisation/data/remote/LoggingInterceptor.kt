package com.example.catganisation.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        println("aici123 ${chain.request()}")

        val request: Request = chain.request()

        return chain.proceed(request)
    }
}