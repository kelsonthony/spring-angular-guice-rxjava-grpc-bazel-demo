package com.kelsonthony.costumer.domain.repository;

import com.kelsonthony.costumer.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    //UserEntity findByUsername(String username);
}
