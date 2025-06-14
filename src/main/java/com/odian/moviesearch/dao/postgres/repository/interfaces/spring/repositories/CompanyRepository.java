package com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories;

import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
