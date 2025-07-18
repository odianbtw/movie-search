package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country implements Comparable<Country> {
    private final Integer id;
    private String name;

    @Override
    public int compareTo(Country o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
