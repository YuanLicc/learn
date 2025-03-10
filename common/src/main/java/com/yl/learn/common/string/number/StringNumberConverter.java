package com.yl.learn.common.string.number;

import com.yl.learn.common.converter.Converter;
import com.yl.learn.common.validate.Validator;

public class StringNumberConverter implements Converter<String, Number> {

    private Validator<String> isNumberStringValidator;

    public StringNumberConverter(Validator<String> isNumberStringValidator) {
        this.isNumberStringValidator = isNumberStringValidator;
    }

    @Override
    public Number convert(String converted) {
        return null;
    }

}
