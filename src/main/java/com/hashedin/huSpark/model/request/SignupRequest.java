package com.hashedin.huSpark.model.request;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Data
@Setter
public class SignupRequest {

    private String username;


    private String email;



    private String password;


    private String phoneNumber;



    private LocalDate dob;


    private Set<String> role;






}
