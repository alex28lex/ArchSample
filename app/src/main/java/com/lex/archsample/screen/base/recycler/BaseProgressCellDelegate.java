package com.lex.archsample.screen.base.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lex.archsample.R;


public abstract class BaseProgressCellDelegate<T> extends BaseCellDelegate<T> {

    @Override
    public BaseViewHolder<T> holder(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_progress, parent, false);
        return new ProgressViewHolder(view);
    }

    public class ProgressViewHolder extends BaseViewHolder<T> {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(T item) {
            // Do nothing.
        }
    }
}
