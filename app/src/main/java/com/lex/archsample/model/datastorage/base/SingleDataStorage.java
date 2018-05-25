package com.lex.archsample.model.datastorage.base;

import android.support.annotation.NonNull;

import io.reactivex.Single;


public interface SingleDataStorage<T> {

    Single<T> get();

    Single<T> get(@NonNull T defaultValue);

    Single<T> put(@NonNull T value);

    Single<T> remove();
}
