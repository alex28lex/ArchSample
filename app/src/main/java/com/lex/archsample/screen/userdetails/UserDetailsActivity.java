package com.lex.archsample.screen.userdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.util.RoutingUtils;
import com.lex.archsample.R;
import com.lex.archsample.application.di.AppComponentHolder;
import com.lex.archsample.application.util.RoutingUtils;
import com.lex.archsample.screen.base.activity.BaseActivity;
import com.lex.archsample.screen.userdetails.di.DaggerUserDetailsComponent;
import com.lex.archsample.screen.userdetails.di.UserDetailsComponentHolder;

import butterknife.BindView;


public class UserDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final boolean afterRecreation = (savedInstanceState != null);
        if (!afterRecreation) {
            UserDetailsComponentHolder.getInstance().bindComponent(
                    DaggerUserDetailsComponent.builder()
                            .appComponent(AppComponentHolder.getInstance().getComponent())
                            .build()
            );
        }

        RoutingUtils.showFragment(
                this,
                savedInstanceState,
                R.id.container_view_group,
                UserDetailsFragment.newInstance(),
                false
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            UserDetailsComponentHolder.getInstance().unbindComponent();
        }
    }
}
