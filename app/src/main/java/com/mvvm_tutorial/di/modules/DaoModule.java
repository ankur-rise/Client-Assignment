package com.mvvm_tutorial.di.modules;

import com.mvvm_tutorial.data.db.DBManager;
import com.mvvm_tutorial.data.db.DeliveryDao;
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
