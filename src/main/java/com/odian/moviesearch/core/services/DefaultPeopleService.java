package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.PeopleDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.core.model.enums.MediaType;
import com.odian.moviesearch.core.model.utils.Pageable;
import com.odian.moviesearch.core.services.validators.PageableValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DefaultPeopleService implements PeopleService {

    private final PeopleDao peopleDao;
    private final PageableValidator pageableValidator;

    @Transactional
    @Override
    public Person create(Person person) {
        person.getPhoto().setMediaType(MediaType.PHOTO);
        return peopleDao.create(person);
    }

    @Override
    public Person findById(Long id) {
        return peopleDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with this id not found"));
    }

    @Override
    public PagedResponse<Person> findAll(Pageable pageable) {
        pageableValidator.validate(pageable);
        return peopleDao.findAll(pageable);
    }
}
