package com.example.rupeekchallengee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rupeekchallengee.data.apimodel.PlaceOfInterestModel
import com.example.rupeekchallengee.data.repository.BaseViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaceOfInterestViewModel : BaseViewModel() {

    val placeOfInterestData = MutableLiveData<List<PlaceOfInterestModel>>()
    val progress = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String>()

    fun getPlaceOfInterest()
    {
        progress.value = true
        apiServiceInterface?.getPlaceOfInterest()?.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                progress.value = false
                val array = response.body()
                val gson = Gson()
                val type = object : TypeToken<List<PlaceOfInterestModel?>?>() {}.type
                placeOfInterestData.value = gson.fromJson(array, type)
                Log.e("placeOfInterestData  ",placeOfInterestData.value.toString())
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("placeOfInterestError  ",t.toString())
                progress.value = false
                error.value = t.localizedMessage
            }
        })
    }

}