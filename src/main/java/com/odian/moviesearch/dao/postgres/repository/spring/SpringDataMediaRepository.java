package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMediaRepository extends JpaRepository<MediaEntity, Long> {
}
