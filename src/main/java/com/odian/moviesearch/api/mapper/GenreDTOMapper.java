package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.GenreDTO;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreDTOMapper {
    GenreDTO to(Genre source);
    List<GenreDTO> to(List<Genre> genres);

    default Set<Genre> map (List<Integer> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    var genre = new Genre();
                    genre.setId(id);
                    return genre;
                })
                .collect(Collectors.toSet());
    }
}
