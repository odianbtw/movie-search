package com.odian.moviesearch.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleDTOMapper peopleMapper;

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody PersonRequest personRequest) {
        var person = peopleService.create(peopleMapper.dtoRequestToDomain(personRequest));
        URI location = URI.create("/people/" + person.getPersonId().id());
        return ResponseEntity
                .created(location)
                .build();
    }


    @GetMapping("/{id}")
    public PersonDTO findById (@PathVariable Long id) {
        return peopleMapper.domainToDto(peopleService.findById(id));
    }

}
