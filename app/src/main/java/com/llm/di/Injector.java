package com.llm.di;

import com.llm.di.components.DaggerIActivityComponent;
import com.llm.di.components.IActivityComponent;

public class Injector {

    private static IActivityComponent component;

    public static IActivityComponent inject() {
        if (component == null)
            component = DaggerIActivityComponent.builder().build();
        return component;
    }
}
