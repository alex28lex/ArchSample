package com.lex.archsample.application.rest;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestClient {

    @GET("/users")
    Flowable<List<UserDto>> getUsers();

    @GET("/users/{login}")
    Flowable<UserDto> getUser(@Path("login") String login);
}
