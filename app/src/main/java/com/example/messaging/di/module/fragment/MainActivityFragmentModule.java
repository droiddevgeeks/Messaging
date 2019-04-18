package com.example.messaging.di.module.fragment;

import com.example.messaging.ui.fragment.ContactFragment;
import com.example.messaging.ui.fragment.MessagesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract ContactFragment provideContactFragment();

    @ContributesAndroidInjector
    abstract MessagesFragment provideMessageFragment();
}
