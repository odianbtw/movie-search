package com.odian.moviesearch.core.application.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SlugGenerator {



    public static String generateSlug (UUID uuid, String name) {
        String firstPartOfUuid = (uuid.toString().split("-"))[0];
        return slugifyName(name) + "-" + firstPartOfUuid;
    }


    private static String slugifyName (String name) {
        return name
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim()
                .replaceAll("\\s+", "-");
    }
}
