package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.out.FilmRepository;
import com.odian.moviesearch.core.domain.model.Film;
import com.odian.moviesearch.dao.postgres.entity.*;
import com.odian.moviesearch.dao.postgres.mapper.FilmEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataFilmRepository;
import com.odian.moviesearch.dao.postgres.service.util.FilmDaoUtils;
import com.odian.moviesearch.dao.postgres.utils.specification.FilmSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.odian.moviesearch.dao.postgres.service.util.FilmDaoUtils.refactorSortBy;


@Repository
@RequiredArgsConstructor
public class DefaultFilmRepository implements FilmRepository {

    private final SpringDataFilmRepository filmRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final FilmEntityMapper filmEntityMapper;
    private final FilmSpecification filmSpecification;

    @Override
    public Optional<Film> findById(UUID id) {
        var film = filmRepository.findOne(filmSpecification.findById(id))
                .orElse(null);
        entityManager.detach(film);
        return Optional.ofNullable(filmEntityMapper.entityToDomain(film));
    }

    @Override
    public Film save(Film film) {
        final var entity = filmEntityMapper.domainToEntity(film);
        entityManager.persist(entity);
        entity.setTrending(new FilmTrendingEntity(entity));
        entity.setRatings(new FilmRatingsEntity(entity));
        entity.setPopularity(new FilmPopularityEntity(entity));
        var essentialMedia = new EssentialFilmMediaEntity();
        essentialMedia.setFilm(entity);
        essentialMedia.setMedias(entity.getMedias());
        entity.setEssentialMedia(essentialMedia);
        var contributions = entity.getFilmContributions();
        contributions.forEach(t -> {
            t.setId(UUID.randomUUID());
            t.setFilm(entity);
        });
        contributions.forEach(entityManager::persist);
        entity.setDirectors(new HashSet<>(contributions));
        return filmEntityMapper.entityToDomain(entity);
    }

    @Override
    public void delete(UUID id) {
        filmRepository.deleteById(id);
    }

    @Override
    public void update(Film film) {
        var entity = filmEntityMapper.domainToEntity(film);
        var persistent = filmRepository.findOne(filmSpecification.findByIdFetchAll(film.getId()))
                        .orElseThrow(() -> new NotFoundException("Film with this id not found"));
        entityManager.flush();
        FilmDaoUtils.update(persistent, entity, entityManager);
        entityManager.flush();
    }


    @Override
    public PagedResponse<Film> findAll(RequestCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPageable().page(),
                criteria.getPageable().pageSize(),
                Sort.by(Sort.Direction.valueOf(criteria.getOrder().name()),
                        refactorSortBy(criteria.getSortBy()))
        );
        var specification = filmSpecification.findAll(criteria);
        var res = filmRepository.findAll(specification, pageable);
        return new PagedResponse<>(
                res.getTotalElements(),
                res.getTotalPages(),
                res.getSize(),
                res.getNumber(),
                res.get()
                        .map(filmEntityMapper::entityToShortDomain)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

}


