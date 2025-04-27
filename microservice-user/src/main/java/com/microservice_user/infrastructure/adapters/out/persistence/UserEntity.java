package com.microservice_user.infrastructure.adapters.out.persistence;

import com.microservice_user.domain.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String lastname;
    private String documentNumber;
    private String email;
    private String password;
    private LocalDate birthday;
    private RoleEnum role;
    private boolean isEnabled = true;
    private boolean account_no_locked;
    private boolean credentialsNotExpired;

}
