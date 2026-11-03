package com.example.users.testpractice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExternalUserResponse(
        Long id,
        String name,
        String username,
        String email
) {
}
