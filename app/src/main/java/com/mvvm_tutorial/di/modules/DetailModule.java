package com.mvvm_tutorial.di.modules;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;
import com.mvvm_tutorial.di.factory.ViewModelAssistedFactory;
import com.mvvm_tutorial.di.key.ViewModelKey;
import com.mvvm_tutorial.ui.DetailActivity;
import com.mvvm_tutorial.ui.viewmodels.UserDetailViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel.class)
    abstract ViewModelAssistedFactory<? extends ViewModel> bindFactory(UserDetailViewModel.Factory factory);

    @Nullable
    @Provides
    static SavedStateRegistryOwner bindSavedStateRegistryOwner(){
        return null;
    }

    @Nullable
    @Provides
    static Bundle provideDefaultArgs() {
        Bundle bundle = new Bundle();
        bundle.putString("uid", "dagger123");
        return bundle;
    }
}