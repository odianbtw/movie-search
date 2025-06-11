package com.odian.moviesearch.dao.mysql.repositories.interfaces;

import com.odian.moviesearch.dao.mysql.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
}
