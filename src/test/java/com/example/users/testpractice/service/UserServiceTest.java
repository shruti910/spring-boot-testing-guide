package com.example.users.testpractice.service;

import com.example.users.testpractice.dto.UserRequest;
import com.example.users.testpractice.dto.UserResponse;
import com.example.users.testpractice.entity.User;
import com.example.users.testpractice.exception.UserNotFoundException;
import com.example.users.testpractice.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Unit Tests")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserRequest userRequest;
    private User existingUser;

    @BeforeEach
    void setUp() {

        // Common test data
        userRequest = new UserRequest(
                "Alex Morgan",
                "alex@example.com"
        );

        existingUser = new User(
                "Alex Morgan",
                "alex@example.com"
        );
    }

    // 1. CREATE USER

    @Test
    @DisplayName("Should create a user successfully")
    void shouldCreateUserSuccessfully() {

        // Arrange
        when(userRepository.save(any(User.class)))
                .thenReturn(existingUser);

        // Act
        UserResponse response =
                userService.createUser(userRequest);

        // Assert
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals("Alex Morgan", response.name()),
                () -> assertEquals( "alex@example.com", response.email()
                )
        );

        verify(userRepository).save(any(User.class));
    }

    // 2. RETRIEVE EXISTING USER

    @Test
    @DisplayName("Should return user when user exists")
    void shouldReturnUserWhenUserExists() {

        // Arrange
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(existingUser));

        // Act
        UserResponse response = userService.getUser(1L);

        // Assert
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals("Alex Morgan", response.name()),
                () -> assertEquals(
                        "alex@example.com",
                        response.email()
                )
        );

        verify(userRepository).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    // 3. RETRIEVE NON-EXISTING USER
    // Parameterised test: same test, multiple IDs

    @ParameterizedTest
    @ValueSource(longs = {99L, 100L, 101L})
    @DisplayName("Should throw exception when user does not exist")
    void shouldThrowExceptionWhenUserDoesNotExist(Long id) {

        // Arrange
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUser(id)
        );

        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);
    }

    // 4. RETRIEVE ALL USERS

    @Test
    @DisplayName("Should return all users")
    void shouldReturnAllUsers() {

        // Arrange
        User secondUser = new User(
                "Sarah Smith",
                "sarah@example.com"
        );

        when(userRepository.findAll())
                .thenReturn(List.of(existingUser, secondUser));

        // Act
        List<UserResponse> responses = userService.getAllUsers();

        // Assert
        assertAll(
                () -> assertEquals(2, responses.size()),
                () -> assertEquals(
                        "Alex Morgan",
                        responses.get(0).name()
                ),
                () -> assertEquals(
                        "Sarah Smith",
                        responses.get(1).name()
                )
        );

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    // 5. UPDATE USER

    @Test
    @DisplayName("Should update an existing user")
    void shouldUpdateUserSuccessfully() {

        // Arrange
        UserRequest updateRequest = new UserRequest(
                "Alex Smith",
                "alex.smith@example.com"
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(existingUser);

        // Act
        UserResponse response =
                userService.updateUser(1L, updateRequest);

        // Assert
        assertAll(
                () -> assertEquals("Alex Smith", response.name()),
                () -> assertEquals(
                        "alex.smith@example.com",
                        response.email()
                )
        );

        verify(userRepository).findById(1L);
        verify(userRepository).save(existingUser);
        verifyNoMoreInteractions(userRepository);
    }

    // 6. DELETE USER

    @Test
    @DisplayName("Should delete an existing user")
    void shouldDeleteUserSuccessfully() {

        // Arrange
        when(userRepository.existsById(1L))
                .thenReturn(true);

        // Act
        assertDoesNotThrow(
                () -> userService.deleteUser(1L)
        );

        // Assert / Verify
        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Should throw exception when deleting a missing user")
    void shouldThrowExceptionWhenDeletingMissingUser() {

        // Arrange
        when(userRepository.existsById(99L))
                .thenReturn(false);

        // Act and Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.deleteUser(99L)
        );

        verify(userRepository).existsById(99L);

        // Delete must never be called
        verify(userRepository, never()).deleteById(anyLong());

        verifyNoMoreInteractions(userRepository);
    }

    @AfterEach
    void tearDown() {
        // No manual cleanup is required for these tests.
        // MockitoExtension manages the mock lifecycle.
    }
}