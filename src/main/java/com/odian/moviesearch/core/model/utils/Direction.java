package com.odian.moviesearch.core.model.utils;

public enum Direction {
    ASC, DESC;

    public static Direction from(String value) {
        if (value == null) return null;
        try {
            return Direction.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sort direction: " + value + ". Must be ASC or DESC.");
        }
    }
}
