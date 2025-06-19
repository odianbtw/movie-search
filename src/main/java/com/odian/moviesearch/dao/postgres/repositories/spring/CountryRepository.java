package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
