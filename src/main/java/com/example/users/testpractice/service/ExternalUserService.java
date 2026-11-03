package com.example.users.testpractice.service;

import com.example.users.testpractice.client.ExternalUserClient;
import com.example.users.testpractice.dto.ExternalUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {

    private final ExternalUserClient externalUserClient;

    @Autowired
    public ExternalUserService(ExternalUserClient externalUserClient) {

        this.externalUserClient = externalUserClient;
    }

    public ExternalUserResponse getExternalUser(Long id) {
        return externalUserClient.getUserById(id);
    }
}
