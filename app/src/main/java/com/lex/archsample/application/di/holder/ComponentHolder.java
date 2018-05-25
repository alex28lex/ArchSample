package com.lex.archsample.application.di.holder;


public interface ComponentHolder<T> {

    void bindComponent(T component);

    void unbindComponent();

    T getComponent();
}
