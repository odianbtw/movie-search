package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.model.*;
import com.odian.moviesearch.core.model.enums.MediaType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mapper(componentModel = "spring",
    uses = {CountryDTOMapper.class,
            GenreDTOMapper.class,
            CompanyDTOMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieDTOMapper {

    default Movie to (MovieRequest movie){
        if (movie == null) return null;
        Movie m = Movie.builder()
                .name(movie.name())
                .slogan(movie.slogan())
                .description(movie.description())
                .genres(movie.genreIds().stream().map(t -> {
                    Genre genre = new Genre();
                    genre.setId(t);
                    return genre;
                }).toList())
                .countries(movie.countryIds().stream().map(t -> {
                    Country country = new Country();
                    country.setId(t);
                    return country;
                }).toList())
                .companies(movie.companyIds().stream().map(t -> {
                    Company company = new Company();
                    company.setId(t);
                    return company;
                }).toList())
                .releaseDate(movie.releaseDate())
                .durationTime(movie.durationTime())
                .movieType(movie.movieType())
                .movieRating(movie.movieRating())
                .budget(movie.budget())
                .revenue(movie.revenue())
                .build();
        Media cover = new Media(null, movie.coverUrl(), MediaType.COVER);
        Media trailer = new Media(null, movie.trailerUrl(), MediaType.TRAILER);
        m.setMedias(List.of(cover, trailer));
        return m;
    }

    default MovieDTO to (Movie movie) {
        if (movie == null) return null;
        return MovieDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .slogan(movie.getSlogan())
                .description(movie.getDescription())
                .genres(movie.getGenres().stream().map(g -> new GenreDTO(g.getId(), g.getName())).toList())
                .countries(movie.getCountries().stream().map((g -> new CountryDTO(g.getId(), movie.getName()))).toList())
                .companies(movie.getCompanies().stream().map(c -> new CompanyItemDTO(c.getId(), c.getName(), c.getMedia().getUrl())).toList())
                .score(movie.getScore())
                .coverUrl(movie.getMedias().stream().filter(m -> Objects.equals(m.getMediaType(), MediaType.COVER)).map(Media::getUrl).findAny().orElse(null))
                .trailerUrl(movie.getMedias().stream().filter(m -> Objects.equals(m.getMediaType(), MediaType.TRAILER)).map(Media::getUrl).findAny().orElse(null))
                .releaseDate(movie.getReleaseDate())
                .durationTime(movie.getDurationTime())
                .movieType(movie.getMovieType())
                .movieRating(movie.getMovieRating())
                .budget(movie.getBudget())
                .revenue(movie.getRevenue())
                .build();
    }

    default PagedResponseDTO<MovieDTO> to (PagedResponse<Movie> movie){return null;}
}
