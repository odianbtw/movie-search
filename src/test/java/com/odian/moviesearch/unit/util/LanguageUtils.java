package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.Language;
import com.odian.moviesearch.dao.postgres.entity.LanguageEntity;

import java.time.Instant;

public class LanguageUtils {
    public static LanguageEntity getLanguageEntity () {
        return new LanguageEntity(
                1,
                "English",
                Instant.now(),
                Instant.now()
        );
    }

    public static Language getLanguage () {
        return new Language(
                1,
                "English"
        );
    }
}
