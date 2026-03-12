package com.harshraj9988.payment_simulator.controller;

import com.harshraj9988.payment_simulator.dto.AccountDto;
import com.harshraj9988.payment_simulator.entity.Account;
import com.harshraj9988.payment_simulator.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

   private AccountService accountService;

   @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@Valid @RequestBody AccountDto accountDto) {
       AccountDto addedAccount = accountService.createAccount(accountDto);
       return new ResponseEntity<>(addedAccount, HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
       AccountDto account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Long> request) {
       Long amount = request.get("amount");
       AccountDto accountDto = accountService.deposit(id, amount);
       return ResponseEntity.ok(accountDto);
    }

    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Long> request) {
       Long amount = request.get("amount");
       AccountDto accountDto = accountService.withdraw(id, amount);
       return ResponseEntity.ok(accountDto);
    }
}
