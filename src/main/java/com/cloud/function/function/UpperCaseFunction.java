package com.cloud.function.function;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UpperCaseFunction implements Function<String, String> {

    @Override
    public String apply(String text) {
        return text.toUpperCase();
    }
}
