package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.CompanyDTOMapper;
import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.core.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;
    private final CompanyDTOMapper mapper;

    @GetMapping("/{id}")
    public CompanyDTO findById (@PathVariable Long id) {
        return mapper.to(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyRequest companyRequest) {
        var company = mapper.to(
                service.create(mapper.to(companyRequest))
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }
}
