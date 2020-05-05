package com.example.falabellatest.interfaces

import com.example.falabellatest.data.DataPojo
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("api/")
    fun getData(): Call<DataPojo>
}