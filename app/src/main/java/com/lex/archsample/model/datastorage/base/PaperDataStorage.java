package com.lex.archsample.model.datastorage.base;

import android.support.annotation.NonNull;

import io.paperdb.Paper;
import io.reactivex.Single;


public abstract class PaperDataStorage<T> implements DataStorage<T> {

    @Override
    public Single<T> get(@NonNull String key) {
        return Single.fromCallable(() -> Paper.book().read(key));
    }

    @Override
    public Single<T> get(@NonNull String key, @NonNull T defaultValue) {
        return Single.fromCallable(() -> Paper.book().read(key, defaultValue));
    }

    @Override
    public Single<T> put(@NonNull String key, @NonNull T value) {
        return Single.fromCallable(() -> {
            Paper.book().write(key, value);
            return value;
        });
    }

    @Override
    public Single<T> remove(@NonNull String key) {
        return Single.fromCallable(() -> {
            final T item = Paper.book().read(key);
            Paper.book().delete(key);
            return item;
        });
    }
}
