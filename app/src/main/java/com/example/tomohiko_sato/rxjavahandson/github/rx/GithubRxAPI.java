package com.example.tomohiko_sato.rxjavahandson.github.rx;


import com.example.tomohiko_sato.rxjavahandson.github.data.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRxAPI {
    @GET("users/{user}")
    Observable<User> user(@Path("user") String user);
}
