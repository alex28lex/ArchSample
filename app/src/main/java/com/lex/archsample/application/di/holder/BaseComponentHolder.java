package com.lex.archsample.application.di.holder;


public abstract class BaseComponentHolder<T> implements ComponentHolder<T> {
    private T component;

    @Override
    public void bindComponent(T component) {
        this.component = component;
    }

    @Override
    public void unbindComponent() {
        this.component = null;
    }

    @Override
    public T getComponent() {
        return component;
    }
}
