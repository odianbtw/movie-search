package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.MovieCreditDTO;
import com.odian.moviesearch.api.model.MovieCreditRequest;
import com.odian.moviesearch.core.model.MovieCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieCreditDTOMapper {
    @Mapping(target = "person.id", source = "personId")
    MovieCredit to (MovieCreditRequest request);

    MovieCreditDTO to (MovieCredit movieCredit);
}
