package com.example.rupeekchallengee.data.repository

import androidx.lifecycle.ViewModel
import com.example.rupeekchallengee.data.network.RetrofitSDK

open class BaseViewModel:ViewModel() {
    val apiServiceInterface = RetrofitSDK.Builder().build().service
}