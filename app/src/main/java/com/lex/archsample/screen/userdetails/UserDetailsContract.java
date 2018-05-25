package com.lex.archsample.screen.userdetails;

import android.arch.lifecycle.LiveData;

import com.lex.archsample.screen.base.view.MessageView;
import com.lex.archsample.screen.base.view.EmptyView;
import com.lex.archsample.screen.base.view.MessageView;
import com.lex.archsample.screen.base.view.ProgressView;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.viewobject.ViewObject;


public interface UserDetailsContract {

    interface View extends ProgressView, EmptyView, MessageView {

        void setUser(UserVo user);
    }

    interface ViewModel {

        LiveData<ViewObject<UserVo>> getUserLiveData();
    }
}
