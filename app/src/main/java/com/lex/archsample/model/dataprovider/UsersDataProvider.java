package com.lex.archsample.model.dataprovider;

import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;


public interface UsersDataProvider {

    Flowable<List<UserDto>> getUsers();

    Flowable<UserDto> getUser(String login);
}
