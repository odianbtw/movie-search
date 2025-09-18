package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.application.model.PagedResponse;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.domain.model.Keyword;

import java.util.Set;

public interface KeywordService {
    PagedResponse<Keyword> findAll (RequestCriteria criteria);
    Set<Keyword> saveNewKeywords (Set<Keyword> keywords);
}
