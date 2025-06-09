package com.odian.moviesearch.repositories;

import com.odian.moviesearch.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findGenreByName (String name);
    List<Genre> findByNameIn (List<String> names);
}
