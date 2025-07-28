package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.TitleCredit;

public interface TitleCreditRepository {
    void addCreditToMovie (TitleCredit titleCredit);
}
