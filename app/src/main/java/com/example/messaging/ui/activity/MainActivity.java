package com.example.messaging.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;

import com.example.messaging.R;
import com.example.messaging.api.CustomViewModelFactory;
import com.example.messaging.base.BaseActivity;
import com.example.messaging.databinding.ActivityMainTabBinding;
import com.example.messaging.ui.adapter.PagerAdapter;
import com.example.messaging.ui.callback.TabClickListener;
import com.example.messaging.ui.viewmodel.MainActivityViewModel;
import com.example.messaging.util.Utilities;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainTabBinding> {

    @Inject
    CustomViewModelFactory factory;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        setUpViewPager();
        loadContactList();
        loadMessageList();
        observeDataChange();
    }

    private void setUpViewPager() {
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), binding.tlMain.getTabCount());
        binding.vpMain.setAdapter(pageAdapter);
        binding.vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tlMain));
        binding.tlMain.addOnTabSelectedListener(new TabClickListener(binding.vpMain));
        binding.tlMain.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
    }

    private void loadContactList() {
        if (Utilities.isNetworkConnected(this))
            viewModel.getUsers();
        else
            showToast(MainActivity.this, getString(R.string.internet_error));
    }

    private void loadMessageList() {
        if (Utilities.isNetworkConnected(this))
            viewModel.getMessages();
        else
            showToast(MainActivity.this, getString(R.string.internet_error));
    }


    private void observeDataChange() {
        viewModel.getLoading().observe(this, this::showLoading);
        viewModel.getApiError().observe(this, this::showError);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main_tab;
    }

    private void showLoading(Boolean loading) {
        binding.setShowLoading(loading);
    }

    private void showError(Throwable throwable) {
        showToast(MainActivity.this, getErrorMessage(throwable));
    }
}
