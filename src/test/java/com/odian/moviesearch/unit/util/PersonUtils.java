package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.api.model.NamedPersonItemDTO;
import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static NamedPersonItemDTO getNamedPersonItemDto (Person person) {
        return new NamedPersonItemDTO(
                person.getId(),
                person.getName()
        );
    }

    public static Set<NamedPersonItemDTO> getNamedPersonsItemDto (Set<Person> personSet) {
        return personSet.stream()
                .map(PersonUtils::getNamedPersonItemDto)
                .collect(Collectors.toSet());
    }
}
