package com.harshraj9988.payment_simulator.service.impl;

import com.harshraj9988.payment_simulator.dto.AccountDto;
import com.harshraj9988.payment_simulator.entity.Account;
import com.harshraj9988.payment_simulator.mapper.AccountMapper;
import com.harshraj9988.payment_simulator.repository.AccountRepository;
import com.harshraj9988.payment_simulator.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
