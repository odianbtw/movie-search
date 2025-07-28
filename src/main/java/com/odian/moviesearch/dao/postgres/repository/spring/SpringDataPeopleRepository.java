package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPeopleRepository extends JpaRepository<PersonEntity, Long> {
    @EntityGraph(attributePaths = {"country", "medias"})
    Optional<PersonEntity> findById(Long id);
}
