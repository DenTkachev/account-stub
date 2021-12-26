package ru.iteco.account.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction", schema = "ad")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_bank_book_id", referencedColumnName = "id")
    private BankBookEntity sourceBankBookId;

    @ManyToOne
    @JoinColumn(name = "target_bank_book_id", referencedColumnName = "id")
    private BankBookEntity targetBankBookId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "initiation_date")
    private LocalDateTime initiationDate;

    @Column(name = "complete_date")
    private LocalDateTime completeDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusEntity status;


}
