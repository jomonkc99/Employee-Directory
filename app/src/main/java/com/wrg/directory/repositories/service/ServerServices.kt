package com.wrg.directory.repositories.service

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface ServerServices {

    @GET("/v2/5d565297300000680030a986")
    fun getEmployeeList(): Call<JsonArray>
}