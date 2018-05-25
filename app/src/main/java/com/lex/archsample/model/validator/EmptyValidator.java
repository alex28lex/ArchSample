package com.lex.archsample.model.validator;

import android.text.TextUtils;

import com.lex.archsample.model.validator.base.Validator;
import com.lex.archsample.R;
import com.lex.archsample.application.manager.ResExtractor;
import com.lex.archsample.model.validator.base.Validator;


public class EmptyValidator implements Validator<String> {

    @Override
    public boolean isValid(String value) {
        return !TextUtils.isEmpty(value);
    }

    @Override
    public String getDescription() {
        return ResExtractor.getInstance().getString(R.string.error_validation_empty);
    }
}
