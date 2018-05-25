package com.lex.archsample.model.usecase;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataprovider.UsersDataProvider;

import java.util.List;

import io.reactivex.Flowable;


public final class GetUserListStreamUseCase {
    private final UsersDataProvider usersDataProvider;

    public GetUserListStreamUseCase(UsersDataProvider usersDataProvider) {
        this.usersDataProvider = usersDataProvider;
    }

    public Flowable<List<UserDto>> getUserListAsStream() {
        return usersDataProvider.getUsers();
    }
}
