package com.lex.archsample.model.datasource;

import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;


public final class UsersCacheDataSource implements UsersDataSource {

    @Override
    public Flowable<List<UserDto>> getUsers() {
        return Flowable.fromCallable(Collections::emptyList);
    }

    @Override
    public Flowable<UserDto> getUser(String login) {
        return Flowable.fromCallable(() -> UserDto.builder().build());
    }
}
