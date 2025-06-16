package com.odian.moviesearch.core.model;

import java.util.Set;

public class Movie {
    private Long id;
    private String name;
    private String slogan;
    private String description;
    private Set<Genre> genres;
    private Set<Country> countries;
    private Set<Company> companies;

}
