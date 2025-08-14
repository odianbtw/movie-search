package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;

import java.time.Instant;
import java.util.UUID;

public class KeywordUtils {
    public static KeywordEntity getKeywordEntity () {
        return new KeywordEntity(
                UUID.randomUUID(),
                "some",
                Instant.now(),
                Instant.now()
        );
    }

    public static Keyword getKeyword () {
        return new Keyword(
                UUID.randomUUID(),
                "some"
        );
    }
}
