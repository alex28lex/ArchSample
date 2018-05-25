package com.lex.archsample.application.di;

import android.content.Context;

import com.lex.archsample.model.dataprovider.UsersDataProvider;
import com.lex.archsample.model.dataprovider.UsersDataProvider;

import javax.inject.Singleton;

import dagger.Component;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;


@Singleton
@Component(modules = {
        AppModule.class,
        NavigationModule.class,
        RetrofitModule.class,
        DataSourceModule.class,
        DataProviderModule.class
})
public interface AppComponent {

    Context provideContext();

    NavigatorHolder provideNavigatorHolder();

    Router provideRouter();

    UsersDataProvider provideUsersDataProvider();
}
