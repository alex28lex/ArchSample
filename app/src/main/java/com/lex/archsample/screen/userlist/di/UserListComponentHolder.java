package com.lex.archsample.screen.userlist.di;

import com.lex.archsample.application.di.holder.BaseComponentHolder;
import com.lex.archsample.application.di.holder.BaseComponentHolder;


public final class UserListComponentHolder extends BaseComponentHolder<UserListComponent> {
    private static final UserListComponentHolder instance = new UserListComponentHolder();

    private UserListComponentHolder() {
    }

    public static UserListComponentHolder getInstance() {
        return instance;
    }
}
