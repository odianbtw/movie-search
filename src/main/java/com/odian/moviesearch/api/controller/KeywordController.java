package com.odian.moviesearch.api.controller;

import com.odian.moviesearch.api.mapper.KeywordDTOMapper;
import com.odian.moviesearch.api.model.KeywordDTO;
import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.api.util.KeywordCriteria;
import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.application.port.in.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keywords")
public class KeywordController {

    private final KeywordService keywordService;
    private final KeywordDTOMapper keywordDTOMapper;


    @GetMapping
    public PagedResponseDTO<KeywordDTO> findAll (@KeywordCriteria RequestCriteria criteria) {
        var res = keywordService.findAll(criteria);
        return new PagedResponseDTO<>(
                res.totalItems(),
                res.totalPages(),
                res.pageSize(),
                res.currentPage(),
                res.items().stream()
                        .map(keywordDTOMapper::domainToDto)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }


}
