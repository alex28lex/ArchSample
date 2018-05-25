package com.lex.archsample.screen.viewobject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Value;


@Value
public class ViewObject<T> {
    @NonNull
    Status status;
    @Nullable
    T data;
    @Nullable
    Throwable error;

    public static <T> ViewObject<T> loading() {
        return new ViewObject<>(Status.LOADING, null, null);
    }

    public static <T> ViewObject<T> success(@Nullable T data) {
        return new ViewObject<>(Status.SUCCESS, data, null);
    }

    public static <T> ViewObject<T> error(@Nullable Throwable error) {
        return new ViewObject<>(Status.ERROR, null, error);
    }

    public enum Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
