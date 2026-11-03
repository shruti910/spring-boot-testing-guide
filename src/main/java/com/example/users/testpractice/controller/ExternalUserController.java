package com.example.users.testpractice.controller;

import com.example.users.testpractice.dto.ExternalUserResponse;
import com.example.users.testpractice.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external-users")
public class ExternalUserController {

    private final ExternalUserService externalUserService;

    @Autowired
    public ExternalUserController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    @GetMapping("/{id}")
    public ExternalUserResponse getExternalUser(@PathVariable Long id) {
        return externalUserService.getExternalUser(id);
    }
}
