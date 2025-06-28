package com.odian.moviesearch.dao.postgres.mapper.movie;

import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.GenreEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.company.CompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.model.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {CountryEntityMapper.class, GenreEntityMapper.class, CompanyEntityMapper.class, MediaEntityMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieEntityMapper {
    default MovieEntity to (Movie movie){
        return MovieEntity.builder()
                .name(movie.getName())
                .slogan(movie.getSlogan())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .durationTime(movie.getDurationTime())
                .budget(movie.getBudget())
                .revenue(movie.getRevenue())
                .movieRating(movie.getMovieRating())
                .movieType(movie.getMovieType())
                .genres(movie.getGenres().stream().map(t -> {
                    GenreEntity genre = new GenreEntity();
                    genre.setId(t.getId());
                    return genre;
                }).toList())
                .countries(movie.getCountries().stream().map(t -> {
                    CountryEntity country = new CountryEntity();
                    country.setId(t.getId());
                    return country;
                }).toList())
                .companies(movie.getCompanies().stream().map(t -> {
                    CompanyEntity company = new CompanyEntity();
                    company.setId(t.getId());
                    return company;
                }).toList())
                .medias(movie.getMedias().stream().map(t -> {
                    MediaEntity mediaEntity = new MediaEntity();
                    mediaEntity.setUrl(t.getUrl());
                    mediaEntity.setMediaType(t.getMediaType());
                    return mediaEntity;
                }).toList())
                .build();
    }
    @Mapping(target = "score", source = "score.score")
    Movie to (MovieEntity movie);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "companies", ignore = true)
    @Mapping(target = "score", source = "score.score")
    Movie map (MovieEntity movie);
}
