package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.MovieCredit;

public interface MovieCreditDao {

    MovieCredit create (Long movieId, MovieCredit movieCredit);
}
