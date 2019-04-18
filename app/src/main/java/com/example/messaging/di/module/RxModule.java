package com.example.messaging.di.module;


import com.example.messaging.api.RxSingleSchedulers;
import com.example.messaging.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @AppScope
    @Provides
    public RxSingleSchedulers providesScheduler() {
        return RxSingleSchedulers.DEFAULT;
    }
}
