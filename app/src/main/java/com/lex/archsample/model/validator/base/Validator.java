package com.lex.archsample.model.validator.base;


public interface Validator<T> {

    boolean isValid(T value);

    String getDescription();
}
