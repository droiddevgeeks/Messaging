package com.example.messaging.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.messaging.R;
import com.example.messaging.base.BaseActivity;
import com.example.messaging.databinding.ActivityContactDetailBinding;

import static com.example.messaging.api.ApiConstants.CONTACT_EMAIL;
import static com.example.messaging.api.ApiConstants.CONTACT_FIRST_NAME;
import static com.example.messaging.api.ApiConstants.CONTACT_LAST_NAME;
import static com.example.messaging.api.ApiConstants.CONTACT_NUMBER;

public class ContactDetailActivity extends BaseActivity<ActivityContactDetailBinding> {


    private Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.user_info));

        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            setData();
        }
        binding.bSend.setOnClickListener(view -> goToSMS(bundle));

    }

    private void setData() {
        String fname = bundle.getString(CONTACT_FIRST_NAME);
        String phone = bundle.getString(CONTACT_NUMBER);
        String lname = bundle.getString(CONTACT_LAST_NAME);
        String email = bundle.getString(CONTACT_EMAIL);
        binding.tvName.setText(getString(R.string.name) + fname);
        binding.tvUsername.setText(getString(R.string.user_name) + lname);
        binding.tvPh.setText(getString(R.string.phone) + phone);
        binding.tvEmail.setText(getString(R.string.email) + email);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_contact_detail;
    }

    public void goToSMS(Bundle bundle) {
        Intent intent = new Intent(this, SMSActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
