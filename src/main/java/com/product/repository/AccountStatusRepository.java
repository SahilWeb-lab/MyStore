package com.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {

	public Optional<AccountStatus> findByAccountVerificationToken(String vcode);

	public AccountStatus findByPasswordResetToken(String token);
}
