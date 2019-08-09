package com.mvvm_tutorial.di.modules;

import androidx.lifecycle.ViewModel;
import com.mvvm_tutorial.di.key.ViewModelKey;
import com.mvvm_tutorial.ui.viewmodels.DeliveryItemsViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public  abstract   class DetailModule   {

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryItemsViewModel. class)
    abstract ViewModel bindViewModel(DeliveryItemsViewModel viewModel);
}
