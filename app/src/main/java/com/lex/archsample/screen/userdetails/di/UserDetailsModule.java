package com.lex.archsample.screen.userdetails.di;

import com.lex.archsample.screen.userdetails.UserDetailsViewModelFactory;
import com.lex.archsample.screen.viewobject.UserVoStorage;

import dagger.Module;
import dagger.Provides;


@Module
public class UserDetailsModule {

    @Provides
    protected UserDetailsViewModelFactory provideUserDetailsViewModelFactory(UserVoStorage userVoStorage) {
        return new UserDetailsViewModelFactory(userVoStorage);
    }

    @Provides
    protected UserVoStorage provideUserVoStorage() {
        return new UserVoStorage();
    }
}
