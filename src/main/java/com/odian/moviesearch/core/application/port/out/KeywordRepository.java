package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.domain.model.Keyword;

import java.util.Set;

public interface KeywordRepository {
    Set<Keyword> saveAll (Set<Keyword> keywords);
    PagedResponse<Keyword> findAll(RequestCriteria criteria);
}
