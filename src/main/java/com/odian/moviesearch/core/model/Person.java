package com.odian.moviesearch.core.model;


import com.odian.moviesearch.core.model.enums.MovieRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String biography;
    private Country country;
    private LocalDate birthDate;
    private Media photo;
}
