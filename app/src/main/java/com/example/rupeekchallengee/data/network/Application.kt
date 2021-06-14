package com.example.rupeekchallengee.data.network

import android.app.Application

class Application : Application() {


    override fun onCreate() {
        super.onCreate()
        if (InterceptorHTTPClientCreator.okHttpClient == null) {
            InterceptorHTTPClientCreator.createInterceptorHTTPClient()
        }
    }
}