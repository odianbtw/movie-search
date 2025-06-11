package com.odian.moviesearch.dao.mysql.mapper;


import com.odian.moviesearch.core.model.Genre;
import com.odian.moviesearch.dao.mysql.model.GenreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<Genre> to (List<GenreEntity> genres);
}
