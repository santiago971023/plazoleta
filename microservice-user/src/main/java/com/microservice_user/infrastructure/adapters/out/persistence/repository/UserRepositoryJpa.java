package com.microservice_user.infrastructure.adapters.out.persistence.repository;

import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByDocumentNumber(String documentNumber);

    Optional<UserEntity> findByIdAndRole(Long id, RoleEnum role);

}
