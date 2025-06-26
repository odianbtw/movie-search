package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>, JpaSpecificationExecutor<CompanyEntity> {
    @EntityGraph(attributePaths = {"country", "logo"})
    Page<CompanyEntity> findAll(Specification<CompanyEntity> spec, Pageable pageable);
    @EntityGraph(attributePaths = {"country", "logo"})
    Optional<CompanyEntity> findById (Long id);
}
