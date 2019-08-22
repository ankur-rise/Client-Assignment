package com.llm.di.modules;

import android.content.Context;
import com.llm.di.qualifiers.AppContext;
import com.llm.ui.utils.NetworkUtils;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public NetworkUtils getNetworkUtil(@AppContext Context context){
        return new NetworkUtils(context);
    }

}
