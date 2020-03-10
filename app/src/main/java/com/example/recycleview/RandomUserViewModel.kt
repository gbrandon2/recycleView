package com.example.recycleview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.recycleview.data.DailyWeather
import com.example.recycleview.data.RandomUser

class RandomUserViewModel(application:Application):AndroidViewModel(application){
private var randomUserDao:RandomUserDao
    init {
        randomUserDao=RandomUserDao.getInstance(this.getApplication())

    }
    fun forecast(city:String){

        randomUserDao.forecast(city)
    }
    fun getDays():MutableLiveData<List<DailyWeather>>{
        return randomUserDao.getDays()
    }
    fun addUser(){

        randomUserDao.addUser()
    }
fun getUser():MutableLiveData<List<RandomUser>>{
    return randomUserDao.getUsers()

}

    fun clearList() {
        randomUserDao.clearList() //To change body of created functions use File | Settings | File Templates.
    }
}