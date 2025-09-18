package com.odian.moviesearch.core.application.service;


import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.in.KeywordService;
import com.odian.moviesearch.core.application.port.out.KeywordRepository;
import com.odian.moviesearch.core.domain.model.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DefaultKeywordService implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    public PagedResponse<Keyword> findAll(RequestCriteria criteria) {
        return keywordRepository.findAll(criteria);
    }

    @Override
    @Transactional
    public Set<Keyword> saveNewKeywords(Set<Keyword> keywords) {
        var newKeywords = keywords.stream()
                .filter(k -> k.getId() == null)
                .peek(k -> k.setId(UUID.randomUUID()))
                .collect(Collectors.toSet());
        newKeywords = new HashSet<>(keywordRepository.saveAll(newKeywords));
        return Stream.concat(
                keywords.stream().filter(k -> k.getId() != null),
                newKeywords.stream()
        ).collect(Collectors.toSet());
    }
}
