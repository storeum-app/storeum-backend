package com.storeum.repository;

import com.storeum.model.entity.EmailConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmTokenRepository extends JpaRepository<EmailConfirmToken, Long> {

    Optional<EmailConfirmToken> findByToken(String token);
}
