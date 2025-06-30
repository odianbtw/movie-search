package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.MovieCreditDao;
import com.odian.moviesearch.core.model.MovieCredit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultMovieCreditService implements MovieCreditService {

    private final MovieCreditDao movieCreditDao;

    @Override
    @Transactional
    public MovieCredit create(Long movieId, MovieCredit movieCredit) {
        return movieCreditDao.create(movieId, movieCredit);
    }
}
