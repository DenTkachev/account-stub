package ru.iteco.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "status", source = "status.name")
    @Mapping(target = "sourceBankBook.currency", source = "sourceBankBookId.currency.name")
    @Mapping(target = "targetBankBook.currency", source = "targetBankBookId.currency.name")
    TransactionDto mapToDto(TransactionEntity transactionEntity);
    @Mapping(target = "status.name", source = "status")
    @Mapping(target = "sourceBankBookId.currency.name", source = "sourceBankBook.currency")
    @Mapping(target = "targetBankBookId.currency.name", source = "targetBankBook.currency")
    TransactionEntity mapToEntity(TransactionDto transactionDto);
}
