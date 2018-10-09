package com.android.example.virtualfoodassist.RetroFit

object Common {
    private val GOOGLE_API_URL = "https://maps.googleapis.com/"

    val googlePlayService: IGoogleApiServices
        get() = RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleApiServices::class.java)
}