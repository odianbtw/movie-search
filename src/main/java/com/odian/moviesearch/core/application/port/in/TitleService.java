package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.Title;

public interface TitleService {
    Title create (Title title);
}
