package com.example.rupeekchallengee.data.apimodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceOfInterestModel(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image:String,
    @SerializedName("latitude") val latitude:String,
    @SerializedName("longitude") val longitude:String,
    @SerializedName("address") val address:String):Serializable

