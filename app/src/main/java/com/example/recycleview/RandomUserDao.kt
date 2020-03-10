package com.example.recycleview

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.recycleview.data.DailyWeather
import com.example.recycleview.data.RandomUser
import com.example.recycleview.data.User
import org.json.JSONArray
import org.json.JSONObject

class RandomUserDao private constructor(var context: Context) {
    private val users = MutableLiveData<List<RandomUser>>()
    private val userList = mutableListOf<RandomUser>()
    private val days=MutableLiveData<List<DailyWeather>>()
    private val forecast= mutableListOf<DailyWeather>()

    private var queue: RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object {
        @Volatile
        private var INSTANCE: RandomUserDao? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RandomUserDao(context).also { INSTANCE = it }
            }
    }

    fun addUser() {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject())

    }
    fun forecast(city:String)

    {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject2(city))}

    fun getJsonObject2(city:String):JsonObjectRequest {

        val url =
            "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=metric&appid=e644cefd81d81ce1deb62e6b7981dec1"
    val url2=url;
        return JsonObjectRequest(
            Request.Method.GET, url2, null,
            Response.Listener { response ->

                parseObjectG2(response)
            },
            Response.ErrorListener { error ->
                Log.d("WebRequestTest", "That didn't work ${error.message}")
            }
        )
    }
    fun getJsonObject(): JsonObjectRequest {
        val url = "https://api.openweathermap.org/data/2.5//group?id=3689147,3688689,3688465,3687925,3687238,3685533,3674962,3672486,3668605,3666304&units=metric&appid=e644cefd81d81ce1deb62e6b7981dec1"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                parseObjectG(response)
            },
            Response.ErrorListener { error ->
                Log.d("WebRequestTest", "That didn't work ${error.message}")
            }
        )
    }

    fun getUsers() = users
    private fun parseObjectG(response: JSONObject) {
        var list = RandomUser.getUser(response)

        val size: Int = list.size
        for (i in 0 until size) {
            val user = list[i]
            userList.add(user)
        }
        users.value = userList
    }
    private fun parseObjectG2(response: JSONObject) {
        var list = DailyWeather.getDailyWeather(response)
        forecast.clear()
        val size: Int = list.size

        for (i in 0 until size) {
            val user = list[i]
            forecast.add(user)
        }
        days.value = forecast
    }
    fun getDays()=days
    fun clearList() {
        forecast.clear()
    }
}