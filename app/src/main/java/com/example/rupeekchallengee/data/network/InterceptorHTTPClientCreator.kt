package com.example.rupeekchallengee.data.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

class InterceptorHTTPClientCreator {

    companion object {
        var defaultHttpClient: OkHttpClient? = null
        fun createInterceptorHTTPClient() {
            val interceptor = HttpLoggingInterceptor()
            //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            defaultHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Content-Type", " text/xml")
                            .build()
                        chain.proceed(request)
                    }.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()

        }

        val isHttpClientNull: Boolean
            get() = defaultHttpClient == null

        val okHttpClient: OkHttpClient?
            get() = if (defaultHttpClient != null) {
                defaultHttpClient
            } else null

        fun clearOkHttpClient() {
            defaultHttpClient = null
        }
    }
}