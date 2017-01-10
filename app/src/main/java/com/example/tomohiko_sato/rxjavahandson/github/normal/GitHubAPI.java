package com.example.tomohiko_sato.rxjavahandson.github.normal;

import com.example.tomohiko_sato.rxjavahandson.github.data.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPI {
    @GET("users/{user}")
    Call<User> user(@Path("user") String user);
}