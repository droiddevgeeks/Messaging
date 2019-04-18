package com.example.messaging.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.example.messaging.R;
import com.example.messaging.api.CustomViewModelFactory;
import com.example.messaging.api.model.Message;
import com.example.messaging.base.BaseActivity;
import com.example.messaging.databinding.ActivitySendSmspageBinding;
import com.example.messaging.ui.viewmodel.SendSmsViewModel;
import com.example.messaging.util.Utilities;

import java.util.Random;

import javax.inject.Inject;

import static com.example.messaging.api.ApiConstants.CONTACT_NUMBER;

public class SMSActivity extends BaseActivity<ActivitySendSmspageBinding> {

    private Random random;
    private int otpNumber = 0;
    private Bundle bundle;

    @Inject
    CustomViewModelFactory factory;
    private SendSmsViewModel viewModel;

    @Override
    public int getLayout() {
        return R.layout.activity_send_smspage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.compose));
        viewModel = ViewModelProviders.of(this, factory).get(SendSmsViewModel.class);
        setViewData();
        observeDataChange();
    }

    private void setViewData() {
        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            random = new Random();
            otpNumber = (random.nextInt(899999) + 100000);
            binding.tvReceiver.setText("To : " + bundle.getString(CONTACT_NUMBER));
            binding.tvOtp.setText("Hi, Your OTP is: " + otpNumber);
        }
        binding.btnSms.setOnClickListener(view -> sendOTP());
    }

    private void sendOTP() {
        /**
         * Right now I am hardcoding it to my own number
         */
        String from = "+16786662309";
        String to = "+918433719326";
        String body = "Hi, Your OTP is: " + otpNumber;

        if (Utilities.isNetworkConnected(this))
            viewModel.sendMessage(to, from, body);
        else
            showToast(SMSActivity.this, getString(R.string.internet_error));
    }

    private void observeDataChange() {
        viewModel.getMessageData().observe(this, this::sendMessage);
        viewModel.getLoading().observe(this, this::showLoading);
        viewModel.getApiError().observe(this, this::showError);
    }

    private void sendMessage(Message message) {
        showToast(SMSActivity.this, message.getStatus());
    }

    private void showLoading(Boolean loading) {
        binding.setShowLoading(loading);
    }

    private void showError(Throwable throwable) {
        showToast(SMSActivity.this, getErrorMessage(throwable));
    }
}
