package com.llm.di.modules;

import androidx.lifecycle.ViewModel;
import com.llm.di.key.ViewModelKey;
import com.llm.ui.viewmodels.DeliveryItemsViewModel;
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
