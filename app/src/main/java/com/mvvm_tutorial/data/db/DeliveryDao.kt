package com.mvvm_tutorial.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mvvm_tutorial.data.models.DeliveryItemDataModel

@Dao
interface DeliveryDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (items:List<DeliveryItemDataModel>)

    @RawQuery(observedEntities = arrayOf(DeliveryItemDataModel::class))
    fun get(query: SupportSQLiteQuery): DataSource.Factory<Int, DeliveryItemDataModel>


}