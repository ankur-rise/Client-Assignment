package com.llm.data.models

import androidx.room.*
import java.io.Serializable

@Entity
data class DeliveryItemDataModel constructor(
    @PrimaryKey val id:Int, val description:String, @ColumnInfo(name = "image_url") val imageUrl:String,
    @Embedded val location:LatLongDataModel
) : Serializable