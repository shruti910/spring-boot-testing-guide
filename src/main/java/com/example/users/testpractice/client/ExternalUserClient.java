package com.example.users.testpractice.client;

import com.example.users.testpractice.dto.ExternalUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExternalUserClient {

    private final RestClient restClient;

    public ExternalUserClient(RestClient.Builder builder,
                              @Value("${external-api.base-url}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public ExternalUserResponse getUserById(Long id) {
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(ExternalUserResponse.class);
    }
}
