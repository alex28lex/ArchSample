package com.lex.archsample.screen.base.recycler;


public interface CellDelegateManager<T> {

    @SuppressWarnings("unchecked")
    void setDelegates(CellDelegate<T>... delegates);

    CellDelegate<T> getDelegate(T item);

    CellDelegate<T> getDelegate(int viewType);
}
