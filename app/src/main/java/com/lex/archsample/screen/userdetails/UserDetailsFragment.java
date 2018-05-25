package com.lex.archsample.screen.userdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lex.archsample.R;
import com.lex.archsample.screen.userdetails.di.UserDetailsComponentHolder;
import com.lex.archsample.screen.base.fragment.BaseFragment;
import com.lex.archsample.screen.viewobject.UserVo;

import javax.inject.Inject;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {
    @BindView(R.id.avatar_image_view)
    protected ImageView avatarImageView;
    @BindView(R.id.name_text_view)
    protected TextView nameTextView;
    @BindView(R.id.empty_view)
    protected View emptyView;
    @BindView(R.id.progress_view)
    protected View progressView;

    @Inject
    protected UserDetailsViewModelFactory viewModelFactory;
    protected UserDetailsViewModel viewModel;

    public static UserDetailsFragment newInstance() {
        return new UserDetailsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        UserDetailsComponentHolder.getInstance().getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailsViewModel.class);

        viewModel.getUserLiveData().observe(this, viewObject -> {
            if (viewObject == null) {
                return;
            }

            switch (viewObject.getStatus()) {
                case LOADING:
                    setProgressViewEnabled(true);
                    setEmptyViewEnabled(false);
                    break;
                case SUCCESS:
                    final UserVo user = viewObject.getData();
                    if (user == null) {
                        setProgressViewEnabled(false);
                        setEmptyViewEnabled(true);
                    } else {
                        setProgressViewEnabled(false);
                        setEmptyViewEnabled(false);
                        setUser(user);
                    }
                    break;
                case ERROR:
                    setProgressViewEnabled(false);
                    setEmptyViewEnabled(true);

                    final Throwable throwable = viewObject.getError();
                    if (throwable != null) {
                        showMessage(throwable.getLocalizedMessage());
                    }
                    break;
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //region UserDetailsContract.View
    @Override
    public void setUser(UserVo user) {
        getActivity().setTitle(user.getLogin());

        Glide.with(this)
                .load(user.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(avatarImageView);

        nameTextView.setText(user.getLogin());
    }

    @Override
    public void setProgressViewEnabled(boolean enabled) {
        progressView.setVisibility(enabled ? VISIBLE : GONE);
    }

    @Override
    public void setEmptyViewEnabled(boolean enabled) {
        emptyView.setVisibility(enabled ? VISIBLE : GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(avatarImageView, message, Snackbar.LENGTH_SHORT).show();
    }
    //endregion
}
