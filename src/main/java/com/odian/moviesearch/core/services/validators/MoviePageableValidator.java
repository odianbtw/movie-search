package com.odian.moviesearch.core.services.validators;


import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MoviePageableValidator extends PageableValidator {


    @Override
    public Set<String> getValidSortBy() {
        return Set.of("name", "score.score", "releaseDate");
    }

    @Override
    public Set<String> getValidParameterNames() {
        return Set.of("name", "genreId", "countryId", "scoreFrom", "scoreTo", "yearFrom", "yearTo");
    }

}
