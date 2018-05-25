package com.lex.archsample.application.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;


@Module
public class NavigationModule {
    private final Cicerone<Router> cicerone = Cicerone.create();

    @Singleton
    @Provides
    protected NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    @Singleton
    @Provides
    protected Router provideRouter() {
        return cicerone.getRouter();
    }
}
