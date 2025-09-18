package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.PeopleRepository;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.mapper.PersonEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataPersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DefaultPeopleRepository implements PeopleRepository {

    private final SpringDataPersonRepository personRepository;
    private final PersonEntityMapper personEntityMapper;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Person save(Person person) {
        var entity = personEntityMapper.domainToEntity(person);
        entityManager.persist(entity);
        return personEntityMapper.entityToDomain(entity);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        var res = personRepository.findOneById(id).orElse(null);
        return Optional.ofNullable(personEntityMapper.entityToDomain(res));
    }
}
