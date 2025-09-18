package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.out.KeywordRepository;
import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.dao.postgres.mapper.KeywordEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataKeywordRepository;
import com.odian.moviesearch.dao.postgres.utils.specification.KeywordSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class DefaultKeywordRepository implements KeywordRepository {

    private final SpringDataKeywordRepository keywordRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final KeywordEntityMapper keywordEntityMapper;
    private final KeywordSpecification keywordSpecification;

    @Override
    public Set<Keyword> saveAll(Set<Keyword> keywords) {
        var entities = keywordEntityMapper.domainToEntity(keywords);
        entities.forEach(entityManager::persist);
        entityManager.flush();
        return entities
                .stream()
                .map(keywordEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public PagedResponse<Keyword> findAll(RequestCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPageable().page(),
                criteria.getPageable().pageSize(),
                Sort.by(Sort.Direction.valueOf(criteria.getOrder().name()), criteria.getSortBy())
        );
        var specification = keywordSpecification.findAll(criteria);
        var res = keywordRepository.findAll(specification, pageable);
        return new PagedResponse<>(
                res.getTotalElements(),
                res.getTotalPages(),
                res.getSize(),
                res.getNumber(),
                res.get().map(keywordEntityMapper::entityToDomain).collect(Collectors.toCollection(LinkedHashSet::new))
        );

    }
}
