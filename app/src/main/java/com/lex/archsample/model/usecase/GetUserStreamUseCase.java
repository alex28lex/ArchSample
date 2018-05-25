package com.lex.archsample.model.usecase;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataprovider.UsersDataProvider;

import io.reactivex.Flowable;


public final class GetUserStreamUseCase {
    private final UsersDataProvider usersDataProvider;

    public GetUserStreamUseCase(UsersDataProvider usersDataProvider) {
        this.usersDataProvider = usersDataProvider;
    }

    public Flowable<UserDto> getUserAsStream(String login) {
        return usersDataProvider.getUser(login);
    }
}
