package com.example.messaging.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.messaging.R;

import java.io.IOException;

import dagger.android.AndroidInjection;
import retrofit2.HttpException;

abstract public class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, getLayout());
        super.onCreate(savedInstanceState);
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();

    protected String getErrorMessage(final Throwable error) {
        if (error instanceof HttpException) {
            return getHttpErrorMessage(error);
        } else if (error instanceof Exception) {
            return getString(R.string.some_error);
        }
        return getString(R.string.some_error);
    }

    private String getHttpErrorMessage(Throwable throwable) {
            HttpException exception = (HttpException)throwable;
            return exception.getMessage();
    }

}
