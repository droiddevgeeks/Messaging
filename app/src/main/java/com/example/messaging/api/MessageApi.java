package com.example.messaging.api;


import com.example.messaging.api.model.Contact;
import com.example.messaging.api.model.ContactApiResponse;
import com.example.messaging.api.model.Message;
import com.example.messaging.api.model.MessageApiResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessageApi {

    @GET("/droiddevgeeks/FakeContact/db")
    Single<ContactApiResponse> getUsers();

    @GET("https://api.twilio.com/2010-04-01/Accounts/ACbc35e1a0a90999ebdd444a6c7b22f8c2/Messages.json")
    Single<MessageApiResponse> getMessages();

    @FormUrlEncoded
    @POST("https://api.twilio.com/2010-04-01/Accounts/ACbc35e1a0a90999ebdd444a6c7b22f8c2/Messages.json")
    Single<Message> sendMessage(@Field("To") String to,
                                @Field("From") String from,
                                @Field("Body") String body);
}
