package com.lex.archsample.screen.userdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.UserVoStorage;
import com.lex.archsample.screen.viewobject.ViewObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class UserDetailsViewModel extends ViewModel implements UserDetailsContract.ViewModel {
    private final UserVoStorage userVoStorage;

    private MutableLiveData<ViewObject<UserVo>> userData = new MutableLiveData<>();

    public UserDetailsViewModel(UserVoStorage userVoStorage) {
        this.userVoStorage = userVoStorage;
    }

    @Override
    public LiveData<ViewObject<UserVo>> getUserLiveData() {
        if (userData.getValue() == null) {
            userData.setValue(ViewObject.loading());
            userVoStorage.get()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userVo -> userData.setValue(ViewObject.success(userVo)),
                            throwable -> userData.setValue(ViewObject.error(throwable))
                    );
        }
        return userData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
