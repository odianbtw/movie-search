package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Password {
    private String password;
    private String resetToken;
    private Instant expiresAt;
    private boolean isResetTokenUsed;
}
