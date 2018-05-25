package com.lex.archsample.screen.base.recycler;

import android.view.ViewGroup;


public interface CellDelegate<T> {

    boolean is(T item);

    int type();

    BaseViewHolder<T> holder(ViewGroup parent);

    void bind(BaseViewHolder<T> holder, T item);
}
