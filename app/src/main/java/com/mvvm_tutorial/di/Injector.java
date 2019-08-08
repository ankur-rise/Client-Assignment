package com.mvvm_tutorial.di;

import com.mvvm_tutorial.di.components.DaggerIActivityComponent;
import com.mvvm_tutorial.di.components.IActivityComponent;

public class Injector {

    private static IActivityComponent component;

    public static IActivityComponent inject() {
        if (component == null)
            component = DaggerIActivityComponent.builder().build();
        return component;
    }
}
