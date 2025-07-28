package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.TitleCreditRepository;
import com.odian.moviesearch.core.domain.model.TitleCredit;
import com.odian.moviesearch.dao.postgres.mapper.TitleCreditEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataMovieCreditsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultTitleCreditRepository implements TitleCreditRepository {

    private final SpringDataMovieCreditsRepository movieCreditsRepository;
    private final TitleCreditEntityMapper titleCreditEntityMapper;

    @Override
    public void addCreditToMovie(TitleCredit titleCredit) {
        movieCreditsRepository.save(titleCreditEntityMapper.domainToEntityForMovie(titleCredit));
    }
}
