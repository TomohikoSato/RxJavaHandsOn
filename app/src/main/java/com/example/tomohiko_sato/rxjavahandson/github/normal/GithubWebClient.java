package com.example.tomohiko_sato.rxjavahandson.github.normal;


import com.example.tomohiko_sato.rxjavahandson.github.data.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubWebClient {

    private static final String TAG = GithubWebClient.class.getSimpleName();
    private final GitHubAPI api;

    public GithubWebClient() {
        api = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(GitHubAPI.class);
    }

    public User requestUser(String user) throws IOException {
        Response<User> response = api.user(user).execute();
        return response.body();
    }
}
