package com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories;

import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @EntityGraph(attributePaths = {"country", "logo"})
    Optional<CompanyEntity> findById (Long id);
}
