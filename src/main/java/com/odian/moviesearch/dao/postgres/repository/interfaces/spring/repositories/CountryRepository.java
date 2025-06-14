package com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories;

import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
