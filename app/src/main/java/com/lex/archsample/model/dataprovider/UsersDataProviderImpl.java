package com.lex.archsample.model.dataprovider;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.datasource.UsersDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Flowable;


public final class UsersDataProviderImpl implements UsersDataProvider {
    private final UsersDataSource cacheUsersDataSource;
    private final UsersDataSource restUsersDataSource;

    public UsersDataProviderImpl(UsersDataSource cacheUsersDataSource,
                                 UsersDataSource restUsersDataSource) {
        this.cacheUsersDataSource = cacheUsersDataSource;
        this.restUsersDataSource = restUsersDataSource;
    }

    @Override
    public Flowable<List<UserDto>> getUsers() {
        return Flowable.fromCallable(() -> {
            final List<UserDto> users = new ArrayList<>();
            final AtomicReference<Throwable> throwableReference = new AtomicReference<>();

            cacheUsersDataSource.getUsers().subscribe(users::addAll, throwableReference::set);

            if (users.isEmpty()) {
                throwableReference.set(null);
                restUsersDataSource.getUsers().subscribe(users::addAll, throwableReference::set);
            }

            if (throwableReference.get() != null) {
                throw new RuntimeException(throwableReference.get());
            }

            return users;
        });
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Flowable<UserDto> getUser(String login) {
        return Flowable.fromCallable(() -> {
            final AtomicReference<UserDto> userReference = new AtomicReference<>();
            final AtomicReference<Throwable> throwableReference = new AtomicReference<>();

            cacheUsersDataSource.getUser(login).subscribe(userReference::set, throwableReference::set);

            if (userReference.get() == null || userReference.get().getLogin() == null) {
                throwableReference.set(null);
                restUsersDataSource.getUser(login).subscribe(userReference::set, throwableReference::set);
            }

            if (throwableReference.get() != null) {
                throw new RuntimeException(throwableReference.get());
            }

            return userReference.get();
        });
    }
}
