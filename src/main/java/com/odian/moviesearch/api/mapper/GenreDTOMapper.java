package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreDTOMapper {
    GenreDTO to(Genre source);
    List<GenreDTO> to(List<Genre> genres);

}
