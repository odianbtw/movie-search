package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.Title;

public interface TitleService {
    Title findById (Long id);
    Title create (Title title);
}
