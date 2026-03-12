package com.harshraj9988.payment_simulator.service;

import com.harshraj9988.payment_simulator.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, Double amount);
}
