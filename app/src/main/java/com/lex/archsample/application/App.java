package com.lex.archsample.application;

import android.app.Application;

import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.di.AppModule;
import com.lex.archsample.application.manager.ResExtractor;
import com.lex.archsample.BuildConfig;
import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.di.AppModule;

import com.lex.archsample.application.di.DaggerAppComponent;
import com.lex.archsample.application.manager.ResExtractor;

import io.paperdb.Paper;
import timber.log.Timber;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initPaperDb();
        initResExtractor();
        initAppComponent();
    }

    private void initTimber() {
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new TimberReleaseTree());
    }

    private void initPaperDb() {
        Paper.init(this);
    }

    private void initResExtractor() {
        ResExtractor.getInstance().init(this);
    }

    private void initAppComponent() {
        // There is no need to unbind this component, because the system kills the process,
        // accordingly, all objects created by this process are destroyed.
        AppComponentHolder.getInstance().bindComponent(
                DaggerAppComponent.builder()
                        .appModule(new AppModule(this)) // TODO: Replace with @Component.Builder
                        .build()
        );
    }
}
