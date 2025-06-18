package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.*;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.core.model.Movie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private final MovieDao movieDao;
    private final CountryDao countryDao;
    private final GenreDao genreDao;
    private final MediaDao mediaDao;
    private final CompanyDao companyDao;
    private final GenreDictionary genreDictionary;

    @Transactional
    @Override
    public Movie create(Movie movie) {
        var genres = genreDao.findAllByIds(movie.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        if (genres.size() != movie.getGenres().size()) throw new IllegalStateException("Some of genres weren't found");
        var countries = countryDao.findAllByIds(movie.getCountries().stream().map(Country::getId).collect(Collectors.toSet());
        if (countries.size() != movie.getCountries().size()) throw new IllegalStateException("Some of countries weren't found");
        var companies = companyDao.findAllByIds(movie.getCompanies().stream().map(Company::getId).collect(Collectors.toSet());
        if (companies.size() != movie.getCompanies().size()) throw new IllegalStateException("Some of companies weren't found");
        var medias = mediaDao.saveAll(movie.getMedias());
        if (medias.size() != movie.getMedias().size()) throw new IllegalStateException("Some of medias weren't saved");
//        movie.setGenres(genres);
//        movie.setCountries(countries);
//        movie.setCompanies(companies);
//        movie.setMedias(medias);

        var movieId = movieDao.create(movie).getId();

        var movieGenreAssocs = new MovieGenre(movieId, genreId);
        movie.setMovieGenre(movieGenreAssocs);
        return movieDao.save(movie); // todo: check if just movie obj can be returned
    }
}
