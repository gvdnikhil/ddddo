package com.hashedin.huSpark.services.auth;

import com.hashedin.huSpark.model.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface UserLoginService {

    ResponseEntity<?> loginUser(LoginRequest loginRequest);
}
