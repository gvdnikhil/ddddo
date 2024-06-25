package com.hashedin.huSpark.services.auth.Impl;

import com.hashedin.huSpark.enums.ERole;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.model.request.SignupRequest;
import com.hashedin.huSpark.model.response.MessageResponse;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserSignupServiceImplTest {

    @InjectMocks
    private UserSignupServiceImpl userSignupService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_UsernameExists() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("existingUser");

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        ResponseEntity response = userSignupService.registerUser(signupRequest);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error: Username is already taken!", ((MessageResponse) response.getBody()).getMessage());
    }

    @Test
    void testRegisterUser_EmailExists() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newUser");
        signupRequest.setEmail("existingEmail@example.com");

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        ResponseEntity response = userSignupService.registerUser(signupRequest);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error: Email is already in use!", ((MessageResponse) response.getBody()).getMessage());
    }

    @Test
    void testRegisterUser_AgeUnder18() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newUser");
        signupRequest.setEmail("newEmail@example.com");
        signupRequest.setDob(LocalDate.now().minusYears(17));

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        ResponseEntity response = userSignupService.registerUser(signupRequest);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error: User must be at least 18 years old!", ((MessageResponse) response.getBody()).getMessage());
    }

    @Test
    void testRegisterUser_Success() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newUser");
        signupRequest.setEmail("newEmail@example.com");
        signupRequest.setPassword("password");
        signupRequest.setPhoneNumber("1234567890");
        signupRequest.setDob(LocalDate.now().minusYears(20));
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        signupRequest.setRole(roles);

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(new Role(ERole.ROLE_ADMIN)));

        ResponseEntity response = userSignupService.registerUser(signupRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully!", ((MessageResponse) response.getBody()).getMessage());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
