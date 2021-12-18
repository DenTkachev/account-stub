package ru.iteco.account.service;

import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.dto.UserDto;

import java.math.BigDecimal;

public interface TransactionService {

    TransactionDto findById(Integer id);
    TransactionDto create(TransactionDto transactionDto);
    TransactionDto update(TransactionDto transactionDto);
    void delete(Integer id);
    boolean bankBookToBankBookTransact(Integer sourceId, Integer targetId, BigDecimal sendAmount);
    boolean userToUserTransact(Integer userIdSource, Integer userIdTarget, String currency, BigDecimal sendAmount);
}
