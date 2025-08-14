package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class PersonUtils {
    public static PersonEntity getPersonEntity () {
        return new PersonEntity(
                UUID.randomUUID(),
                "random",
                "Random",
                "SomeBiography",
                MediaUtils.getMediaEntity(),
                Set.of(KeywordUtils.getKeywordEntity()),
                Instant.now(),
                Instant.now()
        );
    }


    public static Person getPerson () {
        return new Person(
                UUID.randomUUID(),
                "slug",
                "Some",
                "bio",
                MediaUtils.getMedia(),
                Set.of(KeywordUtils.getKeyword())
        );
    }
}
