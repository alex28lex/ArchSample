package com.lex.archsample.application.di;

import com.lex.archsample.model.datasource.UsersDataSource;
import com.lex.archsample.model.datasource.UsersMockDataSource;

import dagger.Module;
import dagger.Provides;


@Module
public class MockDataSourceModule {

    @Provides
    protected UsersDataSource provideUsersDataSource() {
        return new UsersMockDataSource();
    }
}
