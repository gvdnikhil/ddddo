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
    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)

    private String password;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;


    @NotNull
    @Past
    private LocalDate dob;

    private Set<String> role;




}
