package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.port.out.MovieRepository;
import com.odian.moviesearch.dao.postgres.mapper.MovieEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.PageableMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataMovieRepository;
import com.odian.moviesearch.dao.postgres.service.util.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DefaultMovieRepository implements MovieRepository {

    private final MovieEntityMapper movieMapper;
    private final SpringDataMovieRepository movieRepository;
    private final PageableMapper pageableMapper;
    private final MovieSpecification movieSpecification;

    @Override
    public Movie create(Movie movie) {
        var entity = movieMapper.domainToEntity(movie);
        return movieMapper.entityToDomain(movieRepository.save(entity));
    }

    @Override
    public Optional<Movie> findById(Long id) {
        var entity = movieRepository.findByIdWithDetails(id).orElse(null);
        return Optional.ofNullable(movieMapper.entityToDomain(entity));
    }

    @Override
    public PagedResponse<Movie> findAll(Pageable pageable) {
        var springPageable = pageableMapper.springPageableFromCustom(pageable);
        var specification = movieSpecification.fromPageable(pageable);
        var page = movieRepository.findAll(specification, springPageable);
        var res = movieRepository.findAllByMovies(page.getContent());
        return new PagedResponse<>(
                page.getTotalElements(),
                page.getTotalPages(),
                pageable.getCurrentPage(),
                (int) pageable.getPageSize(),
                res.stream().map(movieMapper::entityToDomainItem).toList()
        );
    }
}
