package com.wrg.directory.repositories.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.wrg.directory.repositories.service.ServerServices
import com.wrg.directory.repositories.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmployeeRepository {

    fun getEmployeeList(context: Context?): MutableLiveData<JsonArray> {
        val responseData = MutableLiveData<JsonArray>()
        val request = ServiceBuilder.buildService(ServerServices::class.java)

        val call = request.getEmployeeList()
        Log.v("jomon", "urL: " + call.request().url())
        call.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.v("jomon", "code: " + response.code())
                responseData.value = response.body()
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.v("jomon", "error: " + t.message)
                responseData.value = null
            }
        })
        return responseData;
    }
}