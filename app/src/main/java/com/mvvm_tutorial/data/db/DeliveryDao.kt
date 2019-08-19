package com.mvvm_tutorial.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm_tutorial.data.models.DeliveryItemDataModel

@Dao
interface DeliveryDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (items:List<DeliveryItemDataModel>)

    @Query("Select * from DeliveryItemDataModel")
    fun get(): DataSource.Factory<Int, DeliveryItemDataModel>


}