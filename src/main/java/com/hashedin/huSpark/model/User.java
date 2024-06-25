package com.hashedin.huSpark.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })


public class User {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Size(max = 20)
    private String username;

    @Setter
    @NotBlank

    @Size(max = 50)
    @Email
    private String email;

    @Setter
    @NotBlank
    @Size(max = 25)
    private String password;

    @Size(min =10 ,max = 10)
    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @Past
    private LocalDate dob;



    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
