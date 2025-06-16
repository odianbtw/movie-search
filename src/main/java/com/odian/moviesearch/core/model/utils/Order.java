package com.odian.moviesearch.core.model.utils;

public enum Order {
    ASC,
    DESC;

    public static Order fromString (String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (RuntimeException e) {
            return ASC;
        }
    }
}
