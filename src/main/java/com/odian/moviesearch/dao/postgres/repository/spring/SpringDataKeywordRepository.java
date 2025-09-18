package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataKeywordRepository extends JpaRepository<KeywordEntity, UUID>,
        JpaSpecificationExecutor<KeywordEntity> {
}
