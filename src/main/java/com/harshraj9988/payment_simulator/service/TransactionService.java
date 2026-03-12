package com.harshraj9988.payment_simulator.service;

import com.harshraj9988.payment_simulator.dto.AmountRequestDto;
import com.harshraj9988.payment_simulator.dto.TransferRequestDto;

public interface TransactionService {

    TransferRequestDto transfer(TransferRequestDto transferRequestDto);

    AmountRequestDto withdraw(AmountRequestDto amountRequestDto);

    AmountRequestDto deposit(AmountRequestDto amountRequestDto);
}
