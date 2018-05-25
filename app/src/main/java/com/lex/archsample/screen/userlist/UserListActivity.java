package com.lex.archsample.screen.userlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.util.RoutingUtils;
import com.lex.archsample.screen.Screens;
import com.lex.archsample.screen.userdetails.UserDetailsActivity;
import com.lex.archsample.screen.userlist.di.UserListComponentHolder;
import com.lex.archsample.R;
import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.util.RoutingUtils;
import com.lex.archsample.screen.Screens;
import com.lex.archsample.screen.base.activity.BaseActivity;
import com.lex.archsample.screen.userdetails.UserDetailsActivity;
import com.lex.archsample.screen.userlist.di.DaggerUserListComponent;
import com.lex.archsample.screen.userlist.di.UserListComponentHolder;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportAppNavigator;


public class UserListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected NavigatorHolder navigatorHolder;

    private Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setSupportActionBar(toolbar);

        final boolean afterRecreation = (savedInstanceState != null);
        if (!afterRecreation) {
            UserListComponentHolder.getInstance().bindComponent(
                    DaggerUserListComponent.builder()
                            .appComponent(AppComponentHolder.getInstance().getComponent())
                            .build()
            );
            UserListComponentHolder.getInstance().getComponent().inject(this);
        }

        navigator = new SupportAppNavigator(this, R.id.container_view_group) {
            @Override
            protected Intent createActivityIntent(Context context, String screenKey, Object data) {
                switch (screenKey) {
                    case Screens.USER_DETAILS:
                        return new Intent(context, UserDetailsActivity.class);
                    default:
                        return null;
                }
            }

            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                return null;
            }
        };

        RoutingUtils.showFragment(
                this,
                savedInstanceState,
                R.id.container_view_group,
                UserListFragment.newInstance(),
                false
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            UserListComponentHolder.getInstance().unbindComponent();
        }
    }
}
