package com.lex.archsample.screen.userdetails;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.lex.archsample.screen.viewobject.UserVoStorage;


public final class UserDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final UserVoStorage userVoStorage;

    public UserDetailsViewModelFactory(UserVoStorage userVoStorage) {
        this.userVoStorage = userVoStorage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserDetailsViewModel(userVoStorage);
    }
}
