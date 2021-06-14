package com.example.rupeekchallengee.data.apiservice

import com.example.rupeekchallengee.data.apimodel.PlaceOfInterestModel
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceInterface {

    @GET("placesofinterest39c1c48.json")
    fun getPlaceOfInterest(): Call<JsonArray>
}