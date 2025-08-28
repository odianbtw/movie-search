package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.domain.model.*;
import com.odian.moviesearch.dao.postgres.mapper.LanguageEntityMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {
        CountryDTOMapper.class,
        GenreDTOMapper.class,
        KeywordDTOMapper.class,
        LanguageDTOMapper.class,
        PersonDTOMapper.class,
        ProductionStudioDTOMapper.class
    },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmDTOMapper {
    StatisticsDTO domainToDto (Statistics domain);
    ExternalLinksDTO domainToDto (ExternalLinks domain);

    @Mapping(target = "filmMedia.posterUrl", expression = "java(mapPoster(domain))")
    @Mapping(target = "filmMedia.backdropUrl", expression = "java(mapBackdrop(domain))")
    @Mapping(target = "filmMedia.trailerUrl", expression = "java(mapTrailer(domain))")
    FilmDetailsDTO domainToDto(FilmDetails domain);

    FilmDTO domainToDto(Film domain);

    default String mapPoster(FilmDetails domain) {
        return domain.getPoster().map(Media::getUrl).orElse(null);
    }

    default String mapBackdrop(FilmDetails domain) {
        return domain.getBackdropImage().map(Media::getUrl).orElse(null);
    }

    default String mapTrailer(FilmDetails domain) {
        return domain.getTrailer().map(Media::getUrl).orElse(null);
    }

    Film dtoToDomain(FilmCreateRequest filmCreateRequest);
}
