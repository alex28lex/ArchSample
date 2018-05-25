package com.lex.archsample.application.manager;

import android.text.TextUtils;

import io.paperdb.Paper;


public final class SessionManager {
    private static final String TAG = SessionManager.class.getSimpleName();
    private static final String KEY_TOKEN = TAG + "_KEY_TOKEN";

    private String token;

    public String getToken() {
        if (TextUtils.isEmpty(token)) {
            token = Paper.book().read(KEY_TOKEN);
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        Paper.book().write(KEY_TOKEN, token);
    }
}
