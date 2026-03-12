package com.harshraj9988.payment_simulator.repository;

import com.harshraj9988.payment_simulator.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
