package com.harshraj9988.payment_simulator.controller;

import com.harshraj9988.payment_simulator.dto.AmountRequestDto;
import com.harshraj9988.payment_simulator.dto.TransferRequestDto;
import com.harshraj9988.payment_simulator.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferRequestDto> transfer(@Valid @RequestBody TransferRequestDto transferRequestDto) {
        TransferRequestDto completedTransferRequestDto = transactionService.transfer(transferRequestDto);
       return new ResponseEntity<>(completedTransferRequestDto, HttpStatus.CREATED);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<AmountRequestDto> withdraw(@Valid @RequestBody AmountRequestDto amountRequestDto) {
        AmountRequestDto completedAmountRequestDto = transactionService.withdraw(amountRequestDto);
        return ResponseEntity.ok(completedAmountRequestDto);
    }

    @PutMapping("/deposit")
    public ResponseEntity<AmountRequestDto> deposit(@Valid @RequestBody AmountRequestDto amountRequestDto) {
        AmountRequestDto completedAmountRequestDto = transactionService.deposit(amountRequestDto);
        return ResponseEntity.ok(completedAmountRequestDto);
    }
}
