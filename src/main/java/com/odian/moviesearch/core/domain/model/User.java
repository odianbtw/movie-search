package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    private final UUID id;
    private String username;
    private String email;
    private Password password;
    private UserRole userRole;
    private boolean emailVerified;
    private UserDetails userDetails;
}
