package ru.iteco.account.service;

import ru.iteco.account.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {

    BankBookDto findById(Integer id);
    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto findByUserIdAndCurrency(Integer userId, String currency);
    BankBookDto create(BankBookDto bankBookDto);
    BankBookDto update(BankBookDto bankBookDto);
    void delete(Integer id);
    void deleteByUserId(Integer userId);
}
