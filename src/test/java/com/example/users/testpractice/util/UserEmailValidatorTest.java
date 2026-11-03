package com.example.users.testpractice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEmailValidatorTest {

    private final UserEmailValidator validator = new UserEmailValidator();

    @Test
    void shouldReturnTrueForValidEmail() {

        // Arrange
        String email = "alex@example.com";

        // Act
        boolean result = validator.isValid(email);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForInvalidEmail() {

        // Arrange
        String email = "alex.example.com";

        // Act
        boolean result = validator.isValid(email);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullEmail() {

        // Act
        boolean result = validator.isValid(null);

        // Assert
        assertFalse(result);
    }
}
