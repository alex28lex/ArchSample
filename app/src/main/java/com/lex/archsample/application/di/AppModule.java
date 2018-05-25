package com.lex.archsample.application.di;

import android.content.Context;

import com.lex.archsample.application.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    protected Context provideContext() {
        return app;
    }
}
