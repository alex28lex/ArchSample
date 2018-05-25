package com.lex.archsample.screen.userlist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lex.archsample.screen.base.recycler.MarginItemDecoration;
import com.lex.archsample.screen.userlist.celldelegate.ProgressCellDelegate;
import com.lex.archsample.screen.userlist.celldelegate.UserCellDelegate;
import com.lex.archsample.screen.userlist.celldelegate.UserListRecyclerObject;
import com.lex.archsample.screen.userlist.di.UserListComponentHolder;
import com.lex.archsample.R;
import com.lex.archsample.screen.base.fragment.BaseFragment;
import com.lex.archsample.screen.base.recycler.BaseCellDelegateAdapter;
import com.lex.archsample.screen.base.recycler.MarginItemDecoration;
import com.lex.archsample.screen.userlist.celldelegate.ProgressCellDelegate;
import com.lex.archsample.screen.userlist.celldelegate.UserCellDelegate;
import com.lex.archsample.screen.userlist.celldelegate.UserListRecyclerObject;
import com.lex.archsample.screen.userlist.di.UserListComponentHolder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class UserListFragment extends BaseFragment implements UserListContract.View {
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    protected View emptyView;
    @BindView(R.id.progress_view)
    protected View progressView;

    @Inject
    protected UserListViewModelFactory viewModelFactory;
    protected UserListContract.ViewModel viewModel;

    private BaseCellDelegateAdapter<UserListRecyclerObject> adapter;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        UserListComponentHolder.getInstance().getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel.class);

        viewModel.getUserListLiveData().observe(this, viewObject -> {
            if (viewObject == null) {
                return;
            }

            switch (viewObject.getStatus()) {
                case LOADING:
                    setProgressViewEnabled(true);
                    setEmptyViewEnabled(false);
                    break;
                case SUCCESS:
                    final List<UserListRecyclerObject> recyclerObjectList = viewObject.getData();
                    if (recyclerObjectList == null || recyclerObjectList.isEmpty()) {
                        setProgressViewEnabled(false);
                        setEmptyViewEnabled(true);
                    } else {
                        setProgressViewEnabled(false);
                        setEmptyViewEnabled(false);
                        adapter.setItems(recyclerObjectList);
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

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final UserCellDelegate userCellDelegate = new UserCellDelegate();
        final ProgressCellDelegate progressCellDelegate = new ProgressCellDelegate();

        adapter = new BaseCellDelegateAdapter<>();
        adapter.setCellDelegates(userCellDelegate, progressCellDelegate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MarginItemDecoration(R.dimen.size_small_2));
        recyclerView.setAdapter(adapter);

        userCellDelegate.setUserClickListener((itemView, position, item) -> {
            viewModel.onUserClick(item);
        });
    }

    @Override
    public void setEmptyViewEnabled(boolean enabled) {
        emptyView.setVisibility(enabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressViewEnabled(boolean enabled) {
        progressView.setVisibility(enabled ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }
}
