package com.lex.archsample.screen.userlist.celldelegate;

import com.lex.archsample.screen.viewobject.ProgressVo;
import com.lex.archsample.screen.viewobject.ProgressVo;
import com.lex.archsample.screen.viewobject.UserVo;

import io.reactivex.annotations.Nullable;


public final class UserListRecyclerObject {
    @Nullable
    public final UserVo user;
    @Nullable
    public final ProgressVo progress;

    private UserListRecyclerObject(UserVo user, ProgressVo progress) {
        this.user = user;
        this.progress = progress;
    }

    public static UserListRecyclerObject newInstance(UserVo user) {
        return new UserListRecyclerObject(user, null);
    }

    public static UserListRecyclerObject newInstance(ProgressVo progress) {
        return new UserListRecyclerObject(null, progress);
    }
}
