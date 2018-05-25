package com.lex.archsample.model.datasource;

import com.lex.archsample.model.dataobject.UserDtoMocker;
import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;


public final class UsersMockDataSource implements UsersDataSource {

    @Override
    public Flowable<List<UserDto>> getUsers() {
        return Flowable.fromCallable(() -> UserDtoMocker.getUserDtoList(30));
    }

    @Override
    public Flowable<UserDto> getUser(String login) {
        return Flowable.fromCallable(() -> UserDtoMocker.getUserDto(login));
    }
}
