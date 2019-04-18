package com.example.messaging.di.module;

import com.example.messaging.di.module.fragment.MainActivityFragmentModule;
import com.example.messaging.ui.activity.ContactDetailActivity;
import com.example.messaging.ui.activity.MainActivity;
import com.example.messaging.ui.activity.SMSActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainActivityFragmentModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract ContactDetailActivity bindContactPageActivity();

    @ContributesAndroidInjector
    abstract SMSActivity bindSMSActivity();

}
