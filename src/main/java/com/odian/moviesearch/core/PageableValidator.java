package com.odian.moviesearch.core;

import com.odian.moviesearch.core.model.utils.Pageable;

public interface PageableValidator {
    void validate (Pageable pageable);
}
