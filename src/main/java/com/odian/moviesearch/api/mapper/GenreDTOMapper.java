package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.domain.model.Genre;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreDTOMapper {
    GenreDTO domainToDto (Genre genre);
    Set<GenreDTO> domainToDto (Set<Genre> genre);
}
