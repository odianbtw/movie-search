package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.CompanyDTOMapper;
import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.api.utils.PageableBinder;
import com.odian.moviesearch.core.services.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;
    private final CompanyDTOMapper mapper;
    private final PageableBinder binder;


    @PostMapping
    public ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyRequest companyRequest) {
        var company = mapper.to(
                service.create(mapper.to(companyRequest))
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update (@PathVariable Long id, @Valid @RequestBody CompanyUpdateDTO companyDTO) {
        if (!Objects.equals(id, companyDTO.id()))
            throw new IllegalArgumentException("Id in the path should be equal to id in the request body");
        var updatedCompany = service.update(mapper.to(companyDTO));
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(mapper.to(updatedCompany));
    }

    @GetMapping("/{id}")
    public CompanyDTO findById (@PathVariable Long id) {
        return mapper.to(service.findById(id));
    }

    @GetMapping
    public PagedResponseDTO<CompanyItemDTO> findAll (HttpServletRequest request) {
        var pageable = binder.pageableFromRequest(request);
        return mapper.to(service.findAll(pageable));
    }

}
