package com.lex.archsample.screen.userlist.di;

import com.lex.archsample.application.di.AppComponent;
import com.lex.archsample.application.di.scope.ActivityScope;
import com.lex.archsample.application.di.AppComponent;
import com.lex.archsample.application.di.scope.ActivityScope;
import com.lex.archsample.screen.userlist.UserListActivity;
import com.lex.archsample.screen.userlist.UserListFragment;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = UserListModule.class)
public interface UserListComponent {

    void inject(UserListActivity activity);

    void inject(UserListFragment fragment);
}
