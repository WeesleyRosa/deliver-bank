package com.deliver.bank.bank.domain.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionDTO {
        private Long id;
        private LocalDateTime dateTime;
        private String toAccount;
        private String fromAccount;
        private BigDecimal value; //BigDecimal = currency symbol or round off
        private Boolean finished;
}
