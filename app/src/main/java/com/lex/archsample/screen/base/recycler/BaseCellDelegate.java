package com.lex.archsample.screen.base.recycler;

import java.util.UUID;


public abstract class BaseCellDelegate<T> implements CellDelegate<T> {
    private final int TYPE = UUID.randomUUID().hashCode();

    @Override
    public int type() {
        return TYPE;
    }

    @Override
    public void bind(BaseViewHolder<T> holder, T item) {
        holder.bind(item);
    }
}
