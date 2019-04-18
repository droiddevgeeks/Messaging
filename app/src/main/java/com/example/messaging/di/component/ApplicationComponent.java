package com.example.messaging.di.component;

import com.example.messaging.MyApplication;
import com.example.messaging.di.module.ActivityBindingModule;
import com.example.messaging.di.module.ApiModule;
import com.example.messaging.di.module.ApplicationModule;
import com.example.messaging.di.module.RxModule;
import com.example.messaging.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApiModule.class,
        RxModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    void inject(MyApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApplication application);

        ApplicationComponent build();
    }
}