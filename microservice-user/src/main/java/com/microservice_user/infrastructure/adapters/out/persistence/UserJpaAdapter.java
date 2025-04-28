package com.microservice_user.infrastructure.adapters.out.persistence;

import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.mapper.UserMapper;
import com.microservice_user.infrastructure.adapters.out.persistence.repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJpaAdapter implements UserRepositoryPort {


    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper;

    public UserJpaAdapter(UserRepositoryJpa userRepositoryJpa, UserMapper userMapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        UserEntity savedEntity = userRepositoryJpa.save(userEntity);
        return userMapper.toUser(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findByEmail(email);
        return userEntityOptional.map(userMapper::toUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findById(id);
        return userEntityOptional.map(userMapper::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryJpa.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return userRepositoryJpa.existsByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<User> findByIdAndRole(Long id, RoleEnum role) {
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findByIdAndRole(id, role);
        return userEntityOptional.map(userMapper::toUser);
    }
}
