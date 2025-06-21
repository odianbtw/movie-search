package com.odian.moviesearch.core.services.validators;

import com.odian.moviesearch.core.model.utils.Pageable;

public interface PageableValidator {
    void validate (Pageable pageable);
}
