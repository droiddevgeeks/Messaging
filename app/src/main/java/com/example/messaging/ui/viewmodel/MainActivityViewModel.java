package com.example.messaging.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.messaging.api.MessageApiClient;
import com.example.messaging.api.RxSingleSchedulers;
import com.example.messaging.api.model.ContactApiResponse;
import com.example.messaging.api.model.MessageApiResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {

    private CompositeDisposable disposable;
    private final MessageApiClient apiClient;
    private final RxSingleSchedulers rxSingleSchedulers;

    private final MutableLiveData<ContactApiResponse> usersData = new MutableLiveData<>();
    private final MutableLiveData<MessageApiResponse> messageData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> apiError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public MainActivityViewModel(MessageApiClient apiClient, RxSingleSchedulers rxSingleSchedulers) {
        this.apiClient = apiClient;
        this.rxSingleSchedulers = rxSingleSchedulers;
        disposable = new CompositeDisposable();
    }

    public MutableLiveData<ContactApiResponse> getUsersData() {
        return usersData;
    }

    public MutableLiveData<Throwable> getApiError() {
        return apiError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<MessageApiResponse> getMessageData() {
        return messageData;
    }

    public void getUsers() {
        loading.postValue(true);
        disposable.add(apiClient
                .getUsers()
                .doOnEvent((list, throwable) -> loading.postValue(false))
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(usersData::postValue, apiError::postValue)
        );
    }

    public void getMessages() {
        loading.setValue(true);
        disposable.add(apiClient
                .getMessages()
                .doOnEvent((list, throwable) -> loading.postValue(false))
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(messageData::postValue, apiError::postValue)
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
