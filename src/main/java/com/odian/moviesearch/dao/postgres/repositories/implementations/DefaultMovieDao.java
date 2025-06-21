package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.MovieDao;
import com.odian.moviesearch.core.model.Movie;
import com.odian.moviesearch.dao.postgres.mapper.MovieEntityMapper;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import com.odian.moviesearch.dao.postgres.model.GenreEntity;
import com.odian.moviesearch.dao.postgres.repositories.spring.CompanyRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.CountryRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.GenreRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultMovieDao implements MovieDao {

    private final MovieEntityMapper mapper;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;
    private final MovieRepository movieRepository;

    @Override
    public Movie create(Movie movie) {
        var movieEntity = mapper.to(movie);
        var genres = genreRepository.findAllById(movieEntity.getGenres().stream().map(GenreEntity::getId).toList());
        var countries = countryRepository.findAllById(movieEntity.getCountries().stream().map(CountryEntity::getId).toList());
        var companies = companyRepository.findAllById(movieEntity.getCompanies().stream().map(CompanyEntity::getId).toList());
        movieEntity.setGenres(genres);
        movieEntity.setCountries(countries);
        movieEntity.setCompanies(companies);
        return mapper.to(
                movieRepository.save(movieEntity)
        );
    }

    @Override
    public Optional<Movie> findById(Long id) {
        var movie = movieRepository.findById(id).orElse(null);
        return Optional.ofNullable(mapper.to(movie));
    }
}
