package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPersonRepository extends JpaRepository<PersonEntity, UUID> {
    @Query("from PersonEntity p left join fetch p.mediaEntity left join fetch p.keywords where p.id = :id")
    Optional<PersonEntity> findOneById (@Param("id") UUID id);
}
