package com.odian.moviesearch.core.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Data
@RequiredArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Country country;
    private String biography;
    private LocalDate birthDate;
}
