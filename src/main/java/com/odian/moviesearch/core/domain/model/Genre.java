package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.comparator.Comparators;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Genre implements Comparable<Genre> {
    private final Integer id;
    private String name;

    @Override
    public int compareTo(Genre o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
