package com.microservice_user.infrastructure.adapters.out.persistence;

import com.microservice_user.domain.RoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING) // Mapeo de enum a String en DB
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Column(name = "account_no_locked")
    private boolean accountNonLocked = true;

    @Column(name = "account_no_expired")
    private boolean accountNonExpired = true;

    @Column(name = "credential_no_expired")
    private boolean credentialsNonExpired = true;

}
