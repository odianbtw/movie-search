package com.odian.moviesearch.repositories;

import com.odian.moviesearch.model.ProductionStudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductionStudioRepository extends JpaRepository<ProductionStudio, Integer> {
    Optional<ProductionStudio> findByName(String name);
    List<ProductionStudio> findByNameIn (List<String> names);
}
