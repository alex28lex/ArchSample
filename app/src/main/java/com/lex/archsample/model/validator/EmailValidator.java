package com.lex.archsample.model.validator;

import android.util.Patterns;

import com.lex.archsample.R;
import com.lex.archsample.application.manager.ResExtractor;
import com.lex.archsample.model.validator.base.Validator;


public class EmailValidator implements Validator<String> {

    @Override
    public boolean isValid(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getDescription() {
        return ResExtractor.getInstance().getString(R.string.error_validation_email);
    }
}
