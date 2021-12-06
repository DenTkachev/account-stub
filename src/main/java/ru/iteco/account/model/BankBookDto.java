package ru.iteco.account.model;

import lombok.Builder;
import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Currency;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;
    private Integer userId;
    @NotBlank(message = "пустой!")
    private String number;
    private BigDecimal amount;
    @Currency
    private String currency;

}
