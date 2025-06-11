package com.odian.moviesearch.core.model;

import com.odian.moviesearch.core.model.enums.Role;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private List<Role> roles;
    private boolean emailVerified;
    private boolean isBanned;
}
