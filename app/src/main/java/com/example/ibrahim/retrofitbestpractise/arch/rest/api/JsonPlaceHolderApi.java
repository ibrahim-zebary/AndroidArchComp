package com.example.ibrahim.retrofitbestpractise.arch.rest.api;

import com.example.ibrahim.retrofitbestpractise.arch.rest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("/users")
    Call<List<User>> getUsers();

    @POST("/users")
    Call<User> createUser(@Body User user);
}
