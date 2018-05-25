package com.lex.archsample.model.datastorage.base;

import android.support.annotation.NonNull;

import io.paperdb.Paper;
import io.reactivex.Single;


public abstract class PaperSingleDataStorage<T> implements SingleDataStorage<T> {

    protected abstract String getKey();

    @Override
    public Single<T> get() {
        return Single.fromCallable(() -> Paper.book().read(getKey()));
    }

    @Override
    public Single<T> get(@NonNull T defaultValue) {
        return Single.fromCallable(() -> Paper.book().read(getKey(), defaultValue));
    }

    @Override
    public Single<T> put(@NonNull T value) {
        return Single.fromCallable(() -> {
            Paper.book().write(getKey(), value);
            return value;
        });
    }

    @Override
    public Single<T> remove() {
        return Single.fromCallable(() -> {
            final T item = Paper.book().read(getKey());
            Paper.book().delete(getKey());
            return item;
        });
    }
}
