package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.MovieRequest;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.core.model.enums.MediaType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring",
    uses = {CountryDTOMapper.class,
            GenreDTOMapper.class,
            CompanyDTOMapper.class})
public interface MovieDTOMapper {
    @Mapping(target = "countries", source = "countryIds")
    @Mapping(target = "genres", source = "genreIds")
    @Mapping(target = "companies", source = "companyIds")
    @Mapping(target = "medias", expression = "java(mapMedia(request.coverUrl(), request.trailerUrl()))")
    Movie to (MovieRequest movie);

    default Set<Media> mapMedia(String coverUrl, String trailerUrl) {
        Set<Media> mediaSet = new HashSet<>();
        if (coverUrl != null) {
            Media cover = new Media();
            cover.setMediaType(MediaType.COVER);
            cover.setUrl(coverUrl);
            mediaSet.add(cover);
        }
        if (trailerUrl != null) {
            Media trailer = new Media();
            trailer.setMediaType(MediaType.TRAILER);
            trailer.setUrl(trailerUrl);
            mediaSet.add(trailer);
        }
        return mediaSet;
    }
}
