package com.odian.moviesearch.model;


import com.odian.moviesearch.model.enums.MediaType;
import jakarta.persistence.*;


@Entity
@Table(name = "medias")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
}
