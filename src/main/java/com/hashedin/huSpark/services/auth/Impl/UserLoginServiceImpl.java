package com.hashedin.huSpark.services.auth.Impl;


import com.hashedin.huSpark.model.request.LoginRequest;
import com.hashedin.huSpark.model.response.JwtResponse;
import com.hashedin.huSpark.services.UserDetailsImpl;
import com.hashedin.huSpark.services.auth.UserLoginService;
import com.hashedin.huSpark.utils.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;





private final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Override
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {



        logger.info(loginRequest.toString());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        logger.info(authentication.toString());


        SecurityContextHolder.getContext().setAuthentication(authentication);



        String jwt = jwtUtils.generateJwtToken(authentication);

        logger.info(jwt);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));


    }
}
