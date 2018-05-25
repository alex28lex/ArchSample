package com.lex.archsample.screen.userdetails.di;

import com.lex.archsample.application.di.holder.BaseComponentHolder;
import com.lex.archsample.application.di.holder.BaseComponentHolder;


public class UserDetailsComponentHolder extends BaseComponentHolder<UserDetailsComponent> {
    private static final UserDetailsComponentHolder instance = new UserDetailsComponentHolder();

    private UserDetailsComponentHolder() {
    }

    public static UserDetailsComponentHolder getInstance() {
        return instance;
    }
}
