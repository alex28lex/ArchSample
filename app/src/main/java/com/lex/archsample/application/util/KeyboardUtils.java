package com.lex.archsample.application.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


@SuppressWarnings("unused")
public final class KeyboardUtils {

    public static void showKeyboard(@NonNull View editTextView) {
        editTextView.requestFocus();

        final Context context = editTextView.getContext();
        final InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editTextView, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyboard(@NonNull View anchorView) {
        final Context context = anchorView.getContext();
        final InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        final IBinder windowToken = anchorView.getWindowToken();

        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void preventShowingKeyboard(@NonNull Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
