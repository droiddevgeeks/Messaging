package com.example.messaging.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.messaging.api.CustomViewModelFactory;
import com.example.messaging.di.scope.ViewModelKey;
import com.example.messaging.ui.viewmodel.MainActivityViewModel;
import com.example.messaging.ui.viewmodel.SendSmsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SendSmsViewModel.class)
    abstract ViewModel bindSendSmsViewModel(SendSmsViewModel sendSmsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindCustomViewModelFactory(CustomViewModelFactory factory);
}
