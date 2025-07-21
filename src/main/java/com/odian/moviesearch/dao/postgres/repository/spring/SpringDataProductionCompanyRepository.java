package com.odian.moviesearch.dao.postgres.repository.spring;


import com.odian.moviesearch.dao.postgres.entity.ProductionCompanyEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataProductionCompanyRepository extends JpaRepository<ProductionCompanyEntity, Long> {
    @EntityGraph(attributePaths = {"country", "medias"})
    Optional<ProductionCompanyEntity> findById(Long id);
}
