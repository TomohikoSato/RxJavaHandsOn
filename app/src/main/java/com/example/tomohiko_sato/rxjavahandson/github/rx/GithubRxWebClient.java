package com.example.tomohiko_sato.rxjavahandson.github.rx;


import com.example.tomohiko_sato.rxjavahandson.github.data.User;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubRxWebClient {
    private final GithubRxAPI api;

    public GithubRxWebClient() {
        api = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GithubRxAPI.class);
    }

    public Observable<User> requestUser(String user) {
        return api.user(user);
    }
}
