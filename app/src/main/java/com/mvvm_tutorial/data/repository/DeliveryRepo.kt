package com.mvvm_tutorial.data.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mvvm_tutorial.data.IDeliveryRepo
import com.mvvm_tutorial.data.RepoBoundaryCallback
import com.mvvm_tutorial.data.db.DeliveryDao
import com.mvvm_tutorial.data.models.RepoResult
import com.mvvm_tutorial.data.network.Apis
import org.jetbrains.annotations.NotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepo @Inject constructor(
    @NotNull private val api: Apis,
    private val dao: DeliveryDao
) : IDeliveryRepo {


    override fun getDeliveryItems(): RepoResult {
        val dataSource = dao.get()
        val callback = RepoBoundaryCallback(api, dao)
        val networkErr = callback.networkErrors

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(DATABASE_PAGE_SIZE)
            .build()

        val pagedList = LivePagedListBuilder(dataSource, pagedListConfig).setBoundaryCallback(callback).build()
        return RepoResult(pagedList, networkErr)
    }


    companion object {
        private const val DATABASE_PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE_HINT = 20
    }
}

