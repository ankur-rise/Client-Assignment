package com.llm.data.models

import android.os.Parcelable
import androidx.room.*
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize


@Entity @Parcelize
data class DeliveryItemDataModel constructor(
    @PrimaryKey val id:Int, val description:String, @ColumnInfo(name = "image_url") val imageUrl:String,
    @Embedded val location:LatLongDataModel
) : Parcelable