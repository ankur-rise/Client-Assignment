package com.llm

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.llm.data.db.DBManager

class CustomApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        DBManager.initDB(this)
        Fresco.initialize(this)
    }

}