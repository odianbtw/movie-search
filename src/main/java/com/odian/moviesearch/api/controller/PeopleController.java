package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.PeopleDTOMapper;
import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.api.model.PersonItemDTO;
import com.odian.moviesearch.api.model.PersonRequest;
import com.odian.moviesearch.api.utils.PageableBinder;
import com.odian.moviesearch.core.services.PeopleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;
    private final PeopleDTOMapper mapper;
    private final PageableBinder binder;

    @PostMapping
    public ResponseEntity<PersonDTO> create (@Valid @RequestBody PersonRequest personRequest) {
        var res = peopleService.create(mapper.to(personRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.to(res));
    }

    @GetMapping("/{id}")
    public PersonDTO findById (@PathVariable Long id) {
        return mapper.to(peopleService.findById(id));
    }

    @GetMapping
    public PagedResponseDTO<PersonItemDTO> findAll (HttpServletRequest request) {
        var pageable = binder.pageableFromRequest(request);
        return mapper.to(peopleService.findAll(pageable));
    }



}
