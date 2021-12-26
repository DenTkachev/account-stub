package ru.iteco.account.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.iteco.account.constants.StatusEnum;
import ru.iteco.account.mapper.TransactionMapper;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.dto.UserDto;
import ru.iteco.account.model.entity.TransactionEntity;
import ru.iteco.account.model.exception.BankBookNotFoundException;
import ru.iteco.account.model.exception.TransactionErrorException;
import ru.iteco.account.model.exception.TransactionNotFoundException;
import ru.iteco.account.model.exception.UserNotFoundException;
import ru.iteco.account.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private BankBookServiceImpl bankBookService;
    private UserServiceImpl userService;
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::mapToDto)
                .orElseThrow(() -> new TransactionNotFoundException("Транзакция не найдена!"));
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        return transactionMapper.mapToDto(transactionRepository.save(transactionMapper.mapToEntity(transactionDto)));
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionDto.getId())
                .orElseThrow(() -> new TransactionNotFoundException("Транзакция не найдена!"));
        return transactionMapper.mapToDto(transactionRepository.save(transactionMapper.mapToEntity(transactionDto)));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }

    public boolean bankBookToBankBookTransact(Integer sourceId, Integer targetId, BigDecimal sendAmount){
         BankBookDto source = bankBookService.findById(sourceId);
         if (source == null){
             throw new BankBookNotFoundException("Счет отправителя перевода не найден.");
         }
         BankBookDto target = bankBookService.findById(targetId);
         if (target == null){
             throw new BankBookNotFoundException("Счет получателя перевода не найден.");
         }
        TransactionDto transaction = TransactionDto.builder()
                .sourceBankBook(source)
                .targetBankBook(target)
                .amount(sendAmount)
                .initiationDate(LocalDateTime.now())
                .status(StatusEnum.PROCESSING.name())
                .build();
         transaction = create(transaction);
         if (source.getAmount().compareTo(sendAmount) < 0){
             transaction.setStatus(StatusEnum.DECLINED.name());
             transaction.setCompleteDate(LocalDateTime.now());
             update(transaction);
             throw new TransactionErrorException("Недостаточно средств на счете отправителя.");
         }
         if (!source.getCurrency().equalsIgnoreCase(target.getCurrency())){
             transaction.setStatus(StatusEnum.DECLINED.name());
             transaction.setCompleteDate(LocalDateTime.now());
             update(transaction);
             throw new TransactionErrorException("Нельзя совершать перевод между счетами с разной валютой.");
         }
         source.setAmount(source.getAmount().subtract(sendAmount));
         target.setAmount(target.getAmount().add(sendAmount));
         bankBookService.update(source);
         bankBookService.update(target);
         transaction.setStatus(StatusEnum.SUCCESSFUL.name());
         transaction.setCompleteDate(LocalDateTime.now());
         update(transaction);
         return true;
    }

    public boolean userToUserTransact(Integer userIdSource, Integer userIdTarget, String currency, BigDecimal sendAmount) {
        BankBookDto bankBookDtoSource = bankBookService.findByUserIdAndCurrency(userIdSource, currency);
        if (bankBookDtoSource == null){
            throw new BankBookNotFoundException("Не найден счет отправителя в требуемой валюте.");
        }
        BankBookDto bankBookDtoTarget = bankBookService.findByUserIdAndCurrency(userIdTarget, currency);
        if (bankBookDtoTarget == null){
            throw new BankBookNotFoundException("Не найден счет получателя в требуемой валюте.");
        }
        TransactionDto transaction = TransactionDto.builder()
                .sourceBankBook(bankBookDtoSource)
                .targetBankBook(bankBookDtoTarget)
                .amount(sendAmount)
                .initiationDate(LocalDateTime.now())
                .status(StatusEnum.PROCESSING.name())
                .build();
        transaction = create(transaction);
        if (bankBookDtoSource.getAmount().compareTo(sendAmount) < 0){
            transaction.setStatus(StatusEnum.DECLINED.name());
            transaction.setCompleteDate(LocalDateTime.now());
            update(transaction);
            throw new TransactionErrorException("Недостаточно средств на счете отправителя.");
        }
        transaction.setStatus(StatusEnum.SUCCESSFUL.name());
        transaction.setCompleteDate(LocalDateTime.now());
        update(transaction);
        return true;
    }
}
