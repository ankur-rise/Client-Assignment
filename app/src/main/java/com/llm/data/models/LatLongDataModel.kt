package com.llm.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLongDataModel constructor(val lat:Double, val lng:Double, val address:String):Parcelable