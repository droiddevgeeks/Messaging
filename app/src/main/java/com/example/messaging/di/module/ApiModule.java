package com.example.messaging.di.module;

import com.example.messaging.api.ApiConstants;
import com.example.messaging.api.MessageApi;
import com.example.messaging.di.scope.AppScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @AppScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient client,Gson gson) {
        return new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @AppScope
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String authToken = Credentials.basic(ApiConstants.ACCOUNT_SID, ApiConstants.ACCOUNT_PASSWORD);
        Interceptor headerAuthorizationInterceptor = chain -> {
            okhttp3.Request request = chain.request();
            Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        };
        httpClient.addInterceptor(headerAuthorizationInterceptor);
        return httpClient.build();
    }


    @AppScope
    @Provides
    MessageApi provideFlickerApi(Retrofit retrofit) {
        return retrofit.create(MessageApi.class);
    }
}
