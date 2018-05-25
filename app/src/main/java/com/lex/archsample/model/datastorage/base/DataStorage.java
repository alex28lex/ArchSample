package com.lex.archsample.model.datastorage.base;

import android.support.annotation.NonNull;

import io.reactivex.Single;


public interface DataStorage<T> {

    Single<T> get(@NonNull String key);

    Single<T> get(@NonNull String key, @NonNull T defaultValue);

    Single<T> put(@NonNull String key, @NonNull T value);

    Single<T> remove(@NonNull String key);
}
