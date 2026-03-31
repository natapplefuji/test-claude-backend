package com.example.finance.dto;

import java.math.BigDecimal;

public class SummaryDTO {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;

    public SummaryDTO(BigDecimal totalIncome, BigDecimal totalExpense) {
        this.totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO;
        this.totalExpense = totalExpense != null ? totalExpense : BigDecimal.ZERO;
        this.balance = this.totalIncome.subtract(this.totalExpense);
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
