package com.microservice_user.application.ports.out;

import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    boolean existsByDocumentNumber(String documentNumber);

    Optional<User> findByIdAndRole(Long id, RoleEnum role);
}
