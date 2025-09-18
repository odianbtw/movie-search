package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import com.odian.moviesearch.core.application.port.in.KeywordService;
import com.odian.moviesearch.core.application.port.in.MediaService;
import com.odian.moviesearch.core.application.port.in.PeopleService;
import com.odian.moviesearch.core.application.port.out.PeopleRepository;
import com.odian.moviesearch.core.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class DefaultPeopleService implements PeopleService {

    private final MediaService mediaService;
    private final KeywordService keywordService;
    private final PeopleRepository peopleRepository;

    @Override
    @Transactional
    public Person save(Person person) {
        person.setId(UUID.randomUUID());
        person.setSlug(SlugGenerator.generateSlug(person.getId(), person.getName()));
        var profilePhoto = mediaService.save(person.getProfilePhoto());
        person.setProfilePhoto(profilePhoto);
        var keywords = keywordService.saveNewKeywords(person.getKeywords());
        person.setKeywords(keywords);
        return peopleRepository.save(person);
    }

    @Override
    public Person findById(UUID id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id not found"));
    }
}
