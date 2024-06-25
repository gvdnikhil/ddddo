package com.hashedin.huSpark.service.userdetails.auth;

import com.hashedin.huSpark.model.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserSignupService {

    ResponseEntity registerUser(SignupRequest singnupRequest);
}
