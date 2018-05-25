package com.lex.archsample.screen.userlist.celldelegate;

import com.lex.archsample.screen.base.recycler.BaseProgressCellDelegate;


public final class ProgressCellDelegate extends BaseProgressCellDelegate<UserListRecyclerObject> {

    @Override
    public boolean is(UserListRecyclerObject item) {
        return item.progress != null;
    }
}
