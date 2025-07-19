package com.odian.moviesearch.dao.postgres.repository.spring;


import com.odian.moviesearch.dao.postgres.entity.ProductionCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductionCompanyRepository extends JpaRepository<ProductionCompanyEntity, Long> {
}
