package com.odian.moviesearch.services;


import com.odian.moviesearch.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


}
