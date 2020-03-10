package com.example.recycleview.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  User(val nombre:String,val main:String,val temp:String,val humidity:String,val imagen:String): Parcelable{}