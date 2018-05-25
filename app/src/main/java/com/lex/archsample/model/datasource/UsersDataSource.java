package com.lex.archsample.model.datasource;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.List;

import io.reactivex.Flowable;


public interface UsersDataSource {

    Flowable<List<UserDto>> getUsers();

    Flowable<UserDto> getUser(String login);
}
