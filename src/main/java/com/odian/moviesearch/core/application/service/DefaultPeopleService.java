package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.in.PeopleService;
import com.odian.moviesearch.core.application.port.out.PeopleRepository;
import com.odian.moviesearch.core.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DefaultPeopleService implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Transactional
    @Override
    public Person create(Person person) {
        return peopleRepository.create(person);
    }

    @Override
    public Person findById(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with this id not found"));
    }
}
