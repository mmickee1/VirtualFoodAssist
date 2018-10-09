package com.android.example.virtualfoodassist.RetroFit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleApiServices {

    @GET
    fun getNearByPlaces(@Url url:String):Call<MyPlaces>
}