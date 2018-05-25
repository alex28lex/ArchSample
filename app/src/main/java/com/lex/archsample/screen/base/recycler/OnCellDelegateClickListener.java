package com.lex.archsample.screen.base.recycler;

import android.view.View;


public interface OnCellDelegateClickListener<T> {
    void onCellDelegateClick(View itemView, int position, T item);
}
