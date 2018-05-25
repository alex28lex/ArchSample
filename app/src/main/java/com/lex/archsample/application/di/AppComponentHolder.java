package com.lex.archsample.application.di;

import com.lex.archsample.application.di.holder.BaseComponentHolder;
import com.lex.archsample.application.di.holder.BaseComponentHolder;


public final class AppComponentHolder extends BaseComponentHolder<AppComponent> {
    private static final AppComponentHolder instance = new AppComponentHolder();

    private AppComponentHolder() {
    }

    public static AppComponentHolder getInstance() {
        return instance;
    }
}
