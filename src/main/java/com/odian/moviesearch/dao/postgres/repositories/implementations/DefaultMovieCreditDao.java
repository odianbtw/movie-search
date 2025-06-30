package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.MovieCreditDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.MovieCredit;
import com.odian.moviesearch.dao.postgres.mapper.MovieCreditEntityMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.MovieCreditRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.MovieRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultMovieCreditDao implements MovieCreditDao {
    private final MovieCreditRepository repository;
    private final MovieRepository movieRepository;
    private final PeopleRepository peopleRepository;
    private final MovieCreditEntityMapper mapper;

    @Override
    public MovieCredit create(Long movieId, MovieCredit movieCredit) {
        var entity = mapper.to(movieCredit);
        var movie = movieRepository.findByIdNotFull(movieId)
                .orElseThrow(() -> new NotFoundException("Movie with this id not found"));
        var person = peopleRepository.findById(entity.getPerson().getId())
                .orElseThrow(() -> new NotFoundException("Person with this id not found"));
        entity.setPerson(person);
        entity.setMovie(movie);
        return mapper.to(repository.save(entity));
    }
}
