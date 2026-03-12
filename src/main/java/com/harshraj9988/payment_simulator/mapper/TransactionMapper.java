package com.harshraj9988.payment_simulator.mapper;

import com.harshraj9988.payment_simulator.dto.AmountRequestDto;
import com.harshraj9988.payment_simulator.dto.TransferRequestDto;
import com.harshraj9988.payment_simulator.entity.Transaction;
import com.harshraj9988.payment_simulator.enums.TransactionType;

import java.time.LocalDateTime;

public class TransactionMapper {

    public static Transaction toTransaction(TransferRequestDto transferRequestDto) {
        return new Transaction(
                transferRequestDto.getId(),
                transferRequestDto.getSourceAccountId(),
                transferRequestDto.getTargetAccountId(),
                transferRequestDto.getAmount(),
                TransactionType.TRANSFER,
                transferRequestDto.getIdempotencyKey(),
                LocalDateTime.now()
        );
    }

    public static Transaction toTransaction(AmountRequestDto amountRequestDto, TransactionType type) {
        return new Transaction(
                amountRequestDto.getId(),
                amountRequestDto.getAccountId(),
                null,
                amountRequestDto.getAmount(),
                type,
                amountRequestDto.getIdempotencyKey(),
                LocalDateTime.now()
        );
    }

    public static TransferRequestDto toTransferRequestDto(Transaction transaction) {
        return new TransferRequestDto(
                transaction.getId(),
                transaction.getSourceAccountId(),
                transaction.getTargetAccountId(),
                transaction.getAmount(),
                transaction.getIdempotencyKey()
        );
    }

    public static AmountRequestDto toAmountRequestDto(Transaction transaction) {
        return new AmountRequestDto(
                transaction.getId(),
                transaction.getSourceAccountId(),
                transaction.getAmount(),
                transaction.getIdempotencyKey()
        );
    }
}
