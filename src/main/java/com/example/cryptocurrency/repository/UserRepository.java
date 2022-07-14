package com.example.cryptocurrency.repository;

import com.example.cryptocurrency.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername (String username);

}
