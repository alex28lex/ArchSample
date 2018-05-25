package com.lex.archsample.model.datastorage;

import com.lex.archsample.model.datastorage.base.PaperSingleDataStorage;
import com.lex.archsample.model.datastorage.base.PaperSingleDataStorage;


public final class UserLoginStorage extends PaperSingleDataStorage<String> {
    private static final String TAG = UserLoginStorage.class.getSimpleName();
    private static final String KEY_LOGIN = TAG + "_KEY_LOGIN";

    @Override
    protected String getKey() {
        return KEY_LOGIN;
    }
}
