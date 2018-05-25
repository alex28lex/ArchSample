package com.lex.archsample.screen.userlist.celldelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lex.archsample.R;
import com.lex.archsample.screen.viewobject.UserVo;
import com.lex.archsample.screen.base.recycler.BaseCellDelegate;
import com.lex.archsample.screen.base.recycler.BaseViewHolder;
import com.lex.archsample.screen.base.recycler.OnCellDelegateClickListener;

import butterknife.BindView;


public final class UserCellDelegate extends BaseCellDelegate<UserListRecyclerObject> {
    private OnCellDelegateClickListener<UserVo> userClickListener;

    public void setUserClickListener(OnCellDelegateClickListener<UserVo> userClickListener) {
        this.userClickListener = userClickListener;
    }

    @Override
    public boolean is(UserListRecyclerObject item) {
        return item.user != null;
    }

    @Override
    public BaseViewHolder<UserListRecyclerObject> holder(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    protected class UserViewHolder extends BaseViewHolder<UserListRecyclerObject> {
        @BindView(R.id.avatar_image_view)
        protected ImageView avatarImageView;
        @BindView(R.id.name_text_view)
        protected TextView nameTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
        }

        @SuppressWarnings("CodeBlock2Expr")
        @Override
        public void bind(UserListRecyclerObject item) {
            final UserVo user = item.user;

            Glide.with(itemView)
                    .load(user.getAvatarUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(avatarImageView);

            nameTextView.setText(user.getLogin());

            if (userClickListener != null) {
                itemView.setOnClickListener(v -> {
                    userClickListener.onCellDelegateClick(itemView, getAdapterPosition(), user);
                });
            }
        }
    }
}
