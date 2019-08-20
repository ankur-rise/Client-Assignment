package com.llm.data.db;

import android.content.Context;
import androidx.room.Room;

import static com.llm.data.db.AppDBKt.DB_NAME;

/**
 *   Provides singleton instance of HoneywellDatabase
 */

public class DBManager {

    private static AppDB appDatabase;

    public static AppDB getDatabaseInstance() {
        return appDatabase;
    }

    public static void initDB(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDB.class,
                    DB_NAME).build();
        }
    }

}
