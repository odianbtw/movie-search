package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.mapper.CompanyMapper;
import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.core.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;
    private final CompanyMapper mapper;

    @PostMapping
    public ResponseEntity<CompanyDTO> create (@RequestBody CompanyRequest companyRequest) {
        var company = mapper.to(
                service.create(mapper.to(companyRequest))
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }
}
