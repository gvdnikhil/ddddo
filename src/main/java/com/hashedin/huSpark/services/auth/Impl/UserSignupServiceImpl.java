package com.hashedin.huSpark.services.auth.Impl;

import com.hashedin.huSpark.controller.AuthController;
import com.hashedin.huSpark.enums.ERole;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.model.request.SignupRequest;
import com.hashedin.huSpark.model.response.MessageResponse;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import com.hashedin.huSpark.services.auth.UserSignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserSignupServiceImpl implements UserSignupService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;


    private final Logger logger = LoggerFactory.getLogger(AuthController.class);






    @Override
    public ResponseEntity registerUser(SignupRequest signUpRequest) {

        // check if user is present
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logger.info("checking if user is present by username");
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            logger.info("checking if user is present by email");
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // validate age
        logger.info("checking age of user ");
        LocalDate dob = signUpRequest.getDob();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dob, currentDate).getYears();
        logger.info("age of user is " + age);

        if (age < 18) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User must be at least 18 years old!"));
        }

        logger.info("creating an user");



        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber(),signUpRequest.getDob());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        logger.info("adding roles to user");
        user.setRoles(roles);

        logger.info(roles.toString());
        logger.info("saving user");
    logger.info(user.toString());
        userRepository.save(user);

        logger.info("user saved successfully");
        logger.info("returning response");

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }
}
