package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.PeopleDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.core.model.utils.Pageable;
import com.odian.moviesearch.dao.postgres.mapper.PageableMapper;
import com.odian.moviesearch.dao.postgres.mapper.PeopleEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.people.PeopleSpecificationMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.CountryRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultPeopleDao implements PeopleDao {

    private final PeopleEntityMapper mapper;
    private final CountryRepository countryRepository;
    private final PeopleRepository peopleRepository;
    private final PageableMapper pageableMapper;
    private final PeopleSpecificationMapper specificationMapper;

    @Override
    public Person create(Person person) {
        var entity = mapper.to(person);
        var country = countryRepository.findById(entity.getCountry().getId())
                        .orElseThrow(() -> new NotFoundException("Country with this id not found"));
        entity.setCountry(country);
        return mapper.to(peopleRepository.save(entity));
    }

    @Override
    public Optional<Person> findById(Long id) {
        var entity = peopleRepository.findById(id).orElse(null);
        return Optional.ofNullable(mapper.to(entity));
    }

    @Override
    public PagedResponse<Person> findAll(Pageable pageable) {
        var page = pageableMapper.to(pageable);
        var specification = specificationMapper.to(pageable.getParameters());
        var result = peopleRepository.findAll(specification, page);
        return new PagedResponse<>(
                result.getTotalElements(),
                result.getTotalPages(),
                pageable.getCurrentPage(),
                pageable.getPageSize(),
                result.get().map(mapper::to).toList()
        );
    }
}
