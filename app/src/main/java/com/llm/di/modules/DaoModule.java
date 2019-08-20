package com.llm.di.modules;

import com.llm.data.db.DBManager;
import com.llm.data.db.DeliveryDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public    class DaoModule   {

    @Provides
    @Singleton
    public DeliveryDao getDeliveryDao() {
        return DBManager.getDatabaseInstance().deliveryDao();
    }

}
