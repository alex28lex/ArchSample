package com.lex.archsample.screen.userlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.Screens;
import com.lex.archsample.screen.userlist.celldelegate.UserListRecyclerObject;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.UserVoMapper;
import com.lex.archsample.screen.viewobject.UserVoStorage;
import com.lex.archsample.screen.viewobject.ViewObject;
import com.lex.archsample.model.usecase.GetUserListStreamUseCase;
import com.lex.archsample.screen.Screens;
import com.lex.archsample.screen.userlist.celldelegate.UserListRecyclerObject;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.UserVoMapper;
import com.lex.archsample.screen.viewobject.UserVoStorage;
import com.lex.archsample.screen.viewobject.ViewObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;


public class UserListViewModel extends ViewModel implements UserListContract.ViewModel {
    private final Router router;
    private final GetUserListStreamUseCase getUserListStreamUseCase;
    private final UserVoStorage userVoStorage;

    private MutableLiveData<ViewObject<List<UserListRecyclerObject>>> userListData = new MutableLiveData<>();
    private Disposable userListDisposable;

    public UserListViewModel(Router router,
                             GetUserListStreamUseCase getUserListStreamUseCase,
                             UserVoStorage userVoStorage) {
        this.router = router;
        this.getUserListStreamUseCase = getUserListStreamUseCase;
        this.userVoStorage = userVoStorage;
    }

    @Override
    public LiveData<ViewObject<List<UserListRecyclerObject>>> getUserListLiveData() {
        if (userListData.getValue() == null) {
            userListData.setValue(ViewObject.loading());
            userListDisposable = getUserListStreamUseCase.getUserListAsStream()
                    .map(UserVoMapper::fromDto)
                    .map(this::mapUsersToRecyclerObjects)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            recyclerObjectList -> userListData.setValue(ViewObject.success(recyclerObjectList)),
                            throwable -> userListData.setValue(ViewObject.error(throwable))
                    );
        }
        return userListData;
    }

    private List<UserListRecyclerObject> mapUsersToRecyclerObjects(List<UserVo> userVoList) {
        final List<UserListRecyclerObject> recyclerObjectList = new ArrayList<>();
        for (UserVo user : userVoList) {
            recyclerObjectList.add(UserListRecyclerObject.newInstance(user));
        }
        return recyclerObjectList;
    }

    @Override
    public void onUserClick(UserVo user) {
        userVoStorage.put(user).subscribeOn(Schedulers.io()).subscribe();
        router.navigateTo(Screens.USER_DETAILS);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        userListData = null;
        userListDisposable.dispose();
    }
}
