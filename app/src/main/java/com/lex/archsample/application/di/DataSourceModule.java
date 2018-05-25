package com.lex.archsample.application.di;

import com.lex.archsample.application.rest.RestClient;
import com.lex.archsample.model.datasource.UsersCacheDataSource;
import com.lex.archsample.model.datasource.UsersDataSource;
import com.lex.archsample.model.datasource.UsersRestDataSource;
import com.lex.archsample.application.rest.RestClient;
import com.lex.archsample.model.datasource.UsersCacheDataSource;
import com.lex.archsample.model.datasource.UsersDataSource;
import com.lex.archsample.model.datasource.UsersRestDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataSourceModule {

    @Named(DiConsts.KEY_CACHE)
    @Singleton
    @Provides
    protected UsersDataSource provideUsersCacheDataSource() {
        return new UsersCacheDataSource();
    }

    @Named(DiConsts.KEY_REST)
    @Singleton
    @Provides
    protected UsersDataSource provideUsersRestDataSource(RestClient restClient) {
        return new UsersRestDataSource(restClient);
    }
}
