package com.lex.archsample.screen.userlist.di;

import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.viewobject.UserVoStorage;
import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.userlist.UserListViewModelFactory;
import com.lex.archsample.screen.viewobject.UserVoStorage;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;


@Module
public class UserListModule {

    @Provides
    protected UserListViewModelFactory provideUserListViewModelFactory(Router router,
                                                                       GetUserListStreamUseCase getUserListStreamUseCase,
                                                                       UserVoStorage userVoStorage) {
        return new UserListViewModelFactory(router, getUserListStreamUseCase, userVoStorage);
    }

    @Provides
    protected GetUserListStreamUseCase provideGetUserListStreamUseCase(UsersDataProvider usersDataProvider) {
        return new GetUserListStreamUseCase(usersDataProvider);
    }

    @Provides
    protected UserVoStorage provideUserVoStorage() {
        return new UserVoStorage();
    }
}
