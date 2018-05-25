package com.lex.archsample.screen.userdetails.di;

import com.lex.archsample.application.di.AppComponent;
import com.lex.archsample.application.di.scope.ActivityScope;
import com.lex.archsample.application.di.AppComponent;
import com.lex.archsample.application.di.scope.ActivityScope;
import com.lex.archsample.screen.userdetails.UserDetailsFragment;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = UserDetailsModule.class)
public interface UserDetailsComponent {

    void inject(UserDetailsFragment fragment);
}
