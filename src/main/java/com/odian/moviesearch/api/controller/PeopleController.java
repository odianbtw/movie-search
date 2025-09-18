package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.PersonDTOMapper;
import com.odian.moviesearch.api.model.PersonCreateRequest;
import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.core.application.port.in.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonDTOMapper personDTOMapper;


    @PostMapping
    public ResponseEntity<Void> save (@Valid @RequestBody PersonCreateRequest request) {
        var res = peopleService.save(personDTOMapper.dtoToDomain(request));
        return ResponseEntity
                .created(URI.create("/people/" + res.getId()))
                .build();
    }

    @GetMapping("/{id}")
    public PersonDTO findById (@PathVariable UUID id) {
        var res = peopleService.findById(id);
        return personDTOMapper.domainToDto(res);
    }

}
