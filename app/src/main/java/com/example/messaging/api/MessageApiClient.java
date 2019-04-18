package com.example.messaging.api;


import com.example.messaging.api.model.ContactApiResponse;
import com.example.messaging.api.model.Message;
import com.example.messaging.api.model.MessageApiResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class MessageApiClient {
    private final MessageApi api;

    @Inject
    public MessageApiClient(MessageApi api) {
        this.api = api;
    }

    public Single<ContactApiResponse> getUsers() {
        return api.getUsers();
    }

    public Single<MessageApiResponse> getMessages() {
        return api.getMessages();
    }

    public Single<Message> sendMessage(String to, String from, String body){
        return api.sendMessage(to,from,body);
    }
}
