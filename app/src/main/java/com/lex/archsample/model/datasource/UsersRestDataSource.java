package com.lex.archsample.model.datasource;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.application.rest.RestClient;
import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;


public final class UsersRestDataSource implements UsersDataSource {
    private final RestClient restClient;

    public UsersRestDataSource(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Flowable<List<UserDto>> getUsers() {
        return restClient.getUsers();
    }

    @Override
    public Flowable<UserDto> getUser(String login) {
        return restClient.getUser(login);
    }
}
