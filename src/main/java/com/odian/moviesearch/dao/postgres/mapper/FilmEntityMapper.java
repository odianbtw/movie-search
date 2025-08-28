package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.core.domain.model.FilmDetails;
import com.odian.moviesearch.core.domain.model.Statistics;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {
        MediaEntityMapper.class,
        PersonEntityMapper.class,
        GenreEntityMapper.class,
        CountryEntityMapper.class,
        ProductionStudioEntityMapper.class,
        LanguageEntityMapper.class,
        KeywordEntityMapper.class
    },
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmEntityMapper {
    @Mapping(target = "medias", source = "essentialMedia")
    FilmDetails entityToDomainDetails (FilmEntity entity);
    @Mapping(target = "rating", source = "ratings.rating")
    @Mapping(target = "amountOfReviews", source = "ratings.amountOfReviews")
    @Mapping(target = "popularity", source = "popularity.rating")
    @Mapping(target = "trending", source = "trending.rating")
    Statistics entityToDomainStatistics (FilmEntity entity);
    @Mapping(target = "externalLinks.imdb", source = "imdb")
    @Mapping(target = "externalLinks.tmdb", source = "tmdb")
    Film entityToDomain (FilmEntity entity);
}
