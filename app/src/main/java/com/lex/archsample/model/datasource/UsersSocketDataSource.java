package com.lex.archsample.model.datasource;

import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;


public final class UsersSocketDataSource implements UsersDataSource {

    // Just for example.

    @Override
    public Flowable<List<UserDto>> getUsers() {
        return null;
    }

    @Override
    public Flowable<UserDto> getUser(String login) {
        return null;
    }
}
