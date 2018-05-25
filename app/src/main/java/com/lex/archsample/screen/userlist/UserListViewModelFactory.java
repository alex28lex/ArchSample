package com.lex.archsample.screen.userlist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.viewobject.UserVoStorage;
import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.viewobject.UserVoStorage;

import ru.terrakok.cicerone.Router;


public final class UserListViewModelFactory implements ViewModelProvider.Factory {
    private final Router router;
    private final GetUserListStreamUseCase getUserListStreamUseCase;
    private final UserVoStorage userVoStorage;

    public UserListViewModelFactory(Router router,
                                    GetUserListStreamUseCase getUserListStreamUseCase,
                                    UserVoStorage userVoStorage) {
        this.router = router;
        this.getUserListStreamUseCase = getUserListStreamUseCase;
        this.userVoStorage = userVoStorage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserListViewModel(router, getUserListStreamUseCase, userVoStorage);
    }
}
