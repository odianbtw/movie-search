package com.odian.moviesearch.core.model;

import java.time.LocalDate;

public class Award {
    private Long id;
    private String name;
    private String description;
    private LocalDate awardedAt;
    private Movie movie;
}
