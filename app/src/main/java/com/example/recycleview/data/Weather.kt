package com.example.recycleview.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  Weather(val temp:String,val main:String,val humidity:String,val date:String,val picture:String): Parcelable{


}