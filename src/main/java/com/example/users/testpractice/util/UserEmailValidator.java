package com.example.users.testpractice.util;

public class UserEmailValidator {

    public boolean isValid(String email) {
        return email != null
                && email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}
