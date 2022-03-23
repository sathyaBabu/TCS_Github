package com.sathya.tcs_github.REST;

import com.sathya.tcs_github.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("users/{user}")
    Call< GithubUser > getFeed(@Path("user") String user);
}

