package com.una.security.token;

import lombok.Data;

import java.time.Instant;

@Data
public class AppToken {
    private Integer userId;
    private String username;
    private String role;
    private Instant expiration;
}
