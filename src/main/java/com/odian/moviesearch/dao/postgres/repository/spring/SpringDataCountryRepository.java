package com.odian.moviesearch.dao.postgres.repository.spring;


import com.odian.moviesearch.dao.postgres.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCountryRepository extends JpaRepository<CountryEntity, Integer> {
}
