package com.lex.archsample.screen.userlist;

import android.arch.lifecycle.LiveData;

import com.lex.archsample.screen.base.view.MessageView;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.ViewObject;
import com.lex.archsample.screen.userlist.celldelegate.UserListRecyclerObject;
import com.lex.archsample.screen.base.view.EmptyView;
import com.lex.archsample.screen.base.view.MessageView;
import com.lex.archsample.screen.base.view.ProgressView;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.ViewObject;

import java.util.List;


public interface UserListContract {

    interface View extends EmptyView, ProgressView, MessageView {

    }

    interface ViewModel {

        LiveData<ViewObject<List<UserListRecyclerObject>>> getUserListLiveData();

        void onUserClick(UserVo user);
    }
}
