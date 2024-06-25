package com.hashedin.huSpark.services.auth;

import com.hashedin.huSpark.model.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserSignupService {

    ResponseEntity registerUser(SignupRequest singnupRequest);
}
