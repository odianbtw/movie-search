package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.TitleCredit;

public interface TitleCreditService {
    void addCreditToMovie (TitleCredit titleCredit);
}
