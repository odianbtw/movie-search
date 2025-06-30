package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.MovieCredit;

public interface MovieCreditService {
    MovieCredit create (Long movieId, MovieCredit movieCredit);
}
