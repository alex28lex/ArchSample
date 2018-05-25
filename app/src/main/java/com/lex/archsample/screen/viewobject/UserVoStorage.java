package com.lex.archsample.screen.viewobject;

import com.lex.archsample.model.datastorage.base.PaperSingleDataStorage;
import com.lex.archsample.model.datastorage.base.PaperSingleDataStorage;


public final class UserVoStorage extends PaperSingleDataStorage<UserVo> {
    private static final String TAG = UserVoStorage.class.getSimpleName();
    private static final String KEY_USER = TAG + "_KEY_USER";

    @Override
    protected String getKey() {
        return KEY_USER;
    }
}
