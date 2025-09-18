package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.core.domain.model.FilmDetails;
import com.odian.moviesearch.core.domain.model.Statistics;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import org.mapstruct.*;

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
    @Mapping(target = "statistics", expression = "java(entityToDomainStatistics(entity))")
    @Mapping(target = "directors", source = "directors")
    FilmDetails entityToDomainDetails (FilmEntity entity);
    @Mapping(target = "rating", source = "ratings.rating")
    @Mapping(target = "amountOfReviews", source = "ratings.amountOfRatings")
    @Mapping(target = "popularity", source = "popularity.rating")
    @Mapping(target = "trending", source = "trending.rating")
    Statistics entityToDomainStatistics (FilmEntity entity);

    @Mapping(target = "externalLinks.imdbUrl", source = "imdbUrl")
    @Mapping(target = "externalLinks.tmdbUrl", source = "tmdbUrl")
    @Mapping(target = "details", expression = "java(entityToDomainDetails(entity))")
    Film entityToDomain (FilmEntity entity);

    @Mapping(target = "medias", source = "essentialMedia")
    @Mapping(target = "statistics", expression = "java(entityToDomainStatistics(entity))")
    @Mapping(target = "directors", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "languages", ignore = true)
    @Mapping(target = "studios", ignore = true)
    @Mapping(target = "keywords", ignore = true)
    FilmDetails entityToShortDomainDetails (FilmEntity entity);

    @Mapping(target = "externalLinks.imdbUrl", source = "imdbUrl")
    @Mapping(target = "externalLinks.tmdbUrl", source = "tmdbUrl")
    @Mapping(target = "details", expression = "java(entityToShortDomainDetails(entity))")
    Film entityToShortDomain (FilmEntity entity);

    @Mapping(target = "imdbUrl", source = "externalLinks.imdbUrl")
    @Mapping(target = "tmdbUrl", source = "externalLinks.tmdbUrl")
    @Mapping(target = "tagline", source = "details.tagline")
    @Mapping(target = "description", source = "details.description")
    @Mapping(target = "releaseDate", source = "details.releaseDate")
    @Mapping(target = "runtime", source = "details.runtime")
    @Mapping(target = "medias", source = "details.medias")
    @Mapping(target = "filmContributions", source = "details.directors")
    @Mapping(target = "genres", source = "details.genres")
    @Mapping(target = "countries", source = "details.countries")
    @Mapping(target = "studios", source = "details.studios")
    @Mapping(target = "languages", source = "details.languages")
    @Mapping(target = "keywords", source = "details.keywords")
    FilmEntity domainToEntity (Film film);

}
