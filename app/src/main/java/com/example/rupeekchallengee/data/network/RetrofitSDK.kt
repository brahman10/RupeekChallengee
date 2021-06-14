package com.example.rupeekchallengee.data.network

import com.example.rupeekchallengee.data.apiservice.ApiServiceInterface
import com.example.rupeekchallengee.data.network.InterceptorHTTPClientCreator.Companion.okHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/"

class RetrofitSDK(private val retrofit: Retrofit) {

    var service: ApiServiceInterface? = null
        private set

    /**
     * Builder for [RetrofitSDK]
     */
    class Builder {
        /**
         * Create the [RetrofitSDK] to be used.
         *
         * @return [RetrofitSDK]
         */
        fun build(): RetrofitSDK {
            var retrofit: Retrofit? = null

            if (okHttpClient != null) {
                retrofit = Retrofit.Builder()
                        //.addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .baseUrl(BASE_URL)
                        // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                        .build()
                return RetrofitSDK(retrofit)
            } else {
                val httpClient = OkHttpClient.Builder()
                retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                        .baseUrl(BASE_URL)
                        .build()
            }
            return RetrofitSDK(retrofit)
        }
    }

    private fun createService() {
        service = retrofit.create(ApiServiceInterface::class.java)
    }

    init {
        createService()
    }
}