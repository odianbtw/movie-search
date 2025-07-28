package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.PeopleRepository;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.mapper.PeopleEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataPeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultPeopleRepository implements PeopleRepository {

    private final SpringDataPeopleRepository peopleRepository;
    private final PeopleEntityMapper peopleEntityMapper;


    @Override
    public Person create(Person person) {
        var entity = peopleEntityMapper.domainToEntity(person);
        return peopleEntityMapper.entityToDomain(peopleRepository.save(entity));
    }

    @Override
    public Optional<Person> findById(Long id) {
        var entity = peopleRepository.findById(id).orElse(null);
        return Optional.ofNullable(peopleEntityMapper.entityToDomain(entity));
    }
}
