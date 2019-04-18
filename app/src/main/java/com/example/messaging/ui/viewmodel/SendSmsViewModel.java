package com.example.messaging.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.messaging.api.MessageApiClient;
import com.example.messaging.api.RxSingleSchedulers;
import com.example.messaging.api.model.Message;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SendSmsViewModel extends ViewModel {
    private CompositeDisposable disposable;
    private final MessageApiClient apiClient;
    private final RxSingleSchedulers rxSingleSchedulers;

    private final MutableLiveData<Message> message = new MutableLiveData<>();
    private final MutableLiveData<Throwable> apiError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public SendSmsViewModel(MessageApiClient apiClient, RxSingleSchedulers rxSingleSchedulers) {
        this.apiClient = apiClient;
        this.rxSingleSchedulers = rxSingleSchedulers;
        disposable = new CompositeDisposable();
    }

    public MutableLiveData<Message> getMessageData() {
        return message;
    }

    public MutableLiveData<Throwable> getApiError() {
        return apiError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public void sendMessage(String to, String from, String body) {
        loading.setValue(true);
        disposable.add(apiClient
                .sendMessage(to, from, body)
                .doOnEvent((list, throwable) -> loading.postValue(false))
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(message::postValue, apiError::postValue)
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
