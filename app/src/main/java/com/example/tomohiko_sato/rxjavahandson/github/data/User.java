package com.example.tomohiko_sato.rxjavahandson.github.data;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class User {
    public final String login;
    public final int id;
    public final String avatarUrl;
    public final String gravatarId;
    public final String url;
    public final String htmlUrl;
    public final String followersUrl;
    public final String followingUrl;
    public final String gistsUrl;
    public final String starredUrl;
    public final String subscriptionsUrl;
    public final String organizationsUrl;
    public final String reposUrl;
    public final String eventsUrl;
    public final String receivedEventsUrl;
    public final String type;
    public final boolean siteAdmin;
    public final String name;
    public final String company;
    public final String blog;
    public final String location;
    public final String email;
    public final boolean hireable;
    public final String bio;
    public final int publicRepos;
    public final int publicGists;
    public final int followers;
    public final int following;
    public final String createdAt;
    public final String updatedAt;
}