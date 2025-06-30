package com.odian.moviesearch.core.model;

import com.odian.moviesearch.core.model.enums.MovieRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCredit {
    private Long id;
    private Person person;
    private MovieRole role;
}
