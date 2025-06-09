package com.odian.moviesearch.repositories;

import com.odian.moviesearch.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findCountryByName(String name);
    List<Country> findByNameIn (List<String> names);
}
