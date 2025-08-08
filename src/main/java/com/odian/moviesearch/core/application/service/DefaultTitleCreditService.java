package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.port.out.TitleCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTitleCreditService implements TitleCreditService {

    private final TitleCreditRepository titleCreditRepository;

    @Transactional
    @Override
    public void addCreditToMovie(TitleCredit titleCredit) {
        titleCreditRepository.addCreditToMovie(titleCredit);
    }
}
