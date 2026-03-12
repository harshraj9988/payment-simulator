package com.harshraj9988.payment_simulator.service.impl;

import com.harshraj9988.payment_simulator.dto.AmountRequestDto;
import com.harshraj9988.payment_simulator.dto.TransferRequestDto;
import com.harshraj9988.payment_simulator.entity.Account;
import com.harshraj9988.payment_simulator.entity.Transaction;
import com.harshraj9988.payment_simulator.enums.TransactionType;
import com.harshraj9988.payment_simulator.exception.AccountNotFoundException;
import com.harshraj9988.payment_simulator.exception.DuplicateTransactionException;
import com.harshraj9988.payment_simulator.exception.InsufficientFundsException;
import com.harshraj9988.payment_simulator.exception.InvalidTransactionException;
import com.harshraj9988.payment_simulator.mapper.TransactionMapper;
import com.harshraj9988.payment_simulator.repository.AccountRepository;
import com.harshraj9988.payment_simulator.repository.TransactionRepository;
import com.harshraj9988.payment_simulator.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransferRequestDto transfer(TransferRequestDto transferRequestDto) {
        validateIdempotency(transferRequestDto.getIdempotencyKey());

        if(transferRequestDto.getSourceAccountId().equals(transferRequestDto.getTargetAccountId())) {
            throw new InvalidTransactionException("Cannot transfer to same account");
        }

        Account sourceAccount = findAccountById(transferRequestDto.getSourceAccountId());
        Account targetAccount = findAccountById(transferRequestDto.getTargetAccountId());

        if(sourceAccount.getBalance() < transferRequestDto.getAmount()) {
            throw new InsufficientFundsException("Insufficient Funds");
        }

        Long sourceTotal = sourceAccount.getBalance() - transferRequestDto.getAmount();
        sourceAccount.setBalance(sourceTotal);

        Long targetTotal = targetAccount.getBalance() + transferRequestDto.getAmount();
        targetAccount.setBalance(targetTotal);

        Transaction transaction = TransactionMapper.toTransaction(transferRequestDto);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toTransferRequestDto(savedTransaction);
    }

    @Override
    public AmountRequestDto withdraw(AmountRequestDto amountRequestDto) {
        validateIdempotency(amountRequestDto.getIdempotencyKey());

        Account account = findAccountById(amountRequestDto.getAccountId());

        if(account.getBalance() < amountRequestDto.getAmount()) {
            throw new InsufficientFundsException("Insufficient Funds");
        }

        Long total = account.getBalance() - amountRequestDto.getAmount();
        account.setBalance(total);

        Transaction transaction = TransactionMapper.toTransaction(amountRequestDto, TransactionType.WITHDRAW);

        accountRepository.save(account);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toAmountRequestDto(savedTransaction);
    }

    @Override
    public AmountRequestDto deposit(AmountRequestDto amountRequestDto) {
        validateIdempotency(amountRequestDto.getIdempotencyKey());

        Account account = findAccountById(amountRequestDto.getAccountId());

        Long total = account.getBalance() + amountRequestDto.getAmount();
        account.setBalance(total);

        Transaction transaction = TransactionMapper.toTransaction(amountRequestDto, TransactionType.DEPOSIT);

        accountRepository.save(account);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toAmountRequestDto(savedTransaction);
    }

    private void validateIdempotency(String key) {
        if(transactionRepository.findByIdempotencyKey(key).isPresent()) {
            throw new DuplicateTransactionException("Duplicate Request");
        }
    }

    private Account findAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account does not exist. id: " + id));
    }
}
