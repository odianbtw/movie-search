package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.in.SeriesService;
import com.odian.moviesearch.core.application.port.out.SeriesRepository;
import com.odian.moviesearch.core.domain.model.Series;
import com.odian.moviesearch.core.domain.model.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultSeriesService implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    public Title findById(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Series with this id not found"));
    }

    @Transactional
    @Override
    public Title create(Title title) {
        return seriesRepository.create((Series) title);
    }
}
