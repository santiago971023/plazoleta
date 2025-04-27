package com.microservice_user.application.ports.out;

import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;

import java.util.Optional;

public interface UserRepositoryPort {

    User save(User save);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existByEmail(String email);

    boolean existsByDocumentNumber(String documentNumber);

    Optional<User> findByIdAndRole(Long id, RoleEnum role);
}
