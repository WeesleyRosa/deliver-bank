package com.deliver.bank.bank.domain.transaction.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Transaction {
    @Column (name = "id")
    private Long id;
    @Column (name = "dateTime")
    private LocalDateTime dateTime;
    @Column (name = "toAccount")
    private String toAccount;
    @Column (name = "fromAccount")
    private String fromAccount;
    @Column (name = "value")
    private BigDecimal value; //BigDecimal = currency symbol or round off
    @Column (name = "finished")
    private Boolean finished;
}
