package com.lex.archsample.application.manager;

import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.lex.archsample.application.App;


@SuppressWarnings("unused")
public final class ResExtractor {
    private static final ResExtractor instance = new ResExtractor();

    private App app;

    private ResExtractor() {
    }

    public static ResExtractor getInstance() {
        return instance;
    }

    public void init(App app) {
        this.app = app;
    }

    public String[] getStringArray(@ArrayRes int arrayRes) {
        checkInitialization();
        return app.getResources().getStringArray(arrayRes);
    }

    public String getQuantityString(@PluralsRes int pluralsRes, int quantity, Object... formatArgs) {
        checkInitialization();
        return app.getResources().getQuantityString(pluralsRes, quantity, formatArgs);
    }

    public String getString(@StringRes int stringRes, Object... formatArgs) {
        checkInitialization();
        return app.getResources().getString(stringRes, formatArgs);
    }

    public int getInteger(@IntegerRes int integerRes) {
        checkInitialization();
        return app.getResources().getInteger(integerRes);
    }

    public int getColor(@ColorRes int colorRes) {
        checkInitialization();
        return ContextCompat.getColor(app.getApplicationContext(), colorRes);
    }

    public Drawable getDrawable(@DrawableRes int drawableRes) {
        checkInitialization();
        return ContextCompat.getDrawable(app.getApplicationContext(), drawableRes);
    }

    public int getDimensionPixel(@DimenRes int dimenRes) {
        checkInitialization();
        return app.getResources().getDimensionPixelSize(dimenRes);
    }

    private void checkInitialization() {
        if (app == null) {
            throw new IllegalStateException("ResExtractor is not initialized. " +
                    "You must call the init(App app) method before using this class.");
        }
    }
}
