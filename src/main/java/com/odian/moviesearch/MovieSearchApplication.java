package com.odian.moviesearch;

import com.odian.moviesearch.model.Genre;
import com.odian.moviesearch.repositories.GenreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieSearchApplication.class, args);
    }


}
