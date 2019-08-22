package com.llm.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import com.llm.di.qualifiers.AppContext
import javax.inject.Inject

class NetworkUtils @Inject constructor(@AppContext private val context: Context) {

    fun isConnectedToInternet():Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

}