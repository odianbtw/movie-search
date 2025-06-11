package com.odian.moviesearch.dao.mysql.model;


import com.odian.moviesearch.dao.mysql.model.enums.MediaType;
import com.odian.moviesearch.dao.mysql.model.enums.MediaTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "medias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @Enumerated(EnumType.STRING)
    private MediaTypeEntity mediaType;
}
