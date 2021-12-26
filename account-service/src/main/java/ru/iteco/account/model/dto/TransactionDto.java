package ru.iteco.account.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;
    @NotNull
    private BankBookDto sourceBankBook;
    @NotNull
    private BankBookDto targetBankBook;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime initiationDate;
    private LocalDateTime completeDate;
    @NotBlank(message = "Статус не может быть пуст!")
    private String status;

}
