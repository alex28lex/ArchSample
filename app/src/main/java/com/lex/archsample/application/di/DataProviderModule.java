package com.lex.archsample.application.di;

import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.dataprovider.UsersDataProviderImpl;
import com.lex.archsample.model.datasource.UsersDataSource;
import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.dataprovider.UsersDataProviderImpl;
import com.lex.archsample.model.datasource.UsersDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataProviderModule {

    @Singleton
    @Provides
    protected UsersDataProvider provideUsersDataProvider(
            @Named(DiConsts.KEY_CACHE) UsersDataSource usersCacheDataSource,
            @Named(DiConsts.KEY_REST) UsersDataSource usersRestDataSource) {
        return new UsersDataProviderImpl(usersCacheDataSource, usersRestDataSource);
    }
}
