package com.mvvm_tutorial

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.mvvm_tutorial.data.db.DBManager

class CustomApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        DBManager.initDB(this)
        Fresco.initialize(this)
    }

}