package com.harshraj9988.payment_simulator.mapper;

import com.harshraj9988.payment_simulator.dto.AccountDto;
import com.harshraj9988.payment_simulator.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getName(),
                accountDto.getBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getBalance()
        );
    }
}
