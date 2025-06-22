package com.odian.moviesearch.core.model;


import com.odian.moviesearch.core.model.enums.MovieRole;

import java.time.LocalDate;
import java.util.List;

public class Person {
    private Long id;
    private String name;
    private String biography;
    private Country country;
    private LocalDate birthDate;
    private Media photoUrl;
    private List<MovieRole> roles;
}
