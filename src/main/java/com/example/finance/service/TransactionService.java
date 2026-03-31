package com.example.finance.service;

import com.example.finance.dto.SummaryDTO;
import com.example.finance.model.Transaction;
import com.example.finance.model.TransactionType;
import com.example.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Get all transactions (optionally filtered)
    public List<Transaction> getAllTransactions(String type, String startDate, String endDate) {
        boolean hasType = type != null && !type.isBlank();
        boolean hasDateRange = startDate != null && !startDate.isBlank()
                && endDate != null && !endDate.isBlank();

        if (hasType && hasDateRange) {
            TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return transactionRepository.findByTypeAndDateBetweenOrderByDateDesc(transactionType, start, end);
        } else if (hasType) {
            TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
            return transactionRepository.findByTypeOrderByDateDesc(transactionType);
        } else if (hasDateRange) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return transactionRepository.findByDateBetweenOrderByDateDesc(start, end);
        } else {
            return transactionRepository.findAllByOrderByDateDesc();
        }
    }

    // Get transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Create transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Update transaction
    public Optional<Transaction> updateTransaction(Long id, Transaction updated) {
        return transactionRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setAmount(updated.getAmount());
            existing.setType(updated.getType());
            existing.setCategory(updated.getCategory());
            existing.setDate(updated.getDate());
            existing.setDescription(updated.getDescription());
            return transactionRepository.save(existing);
        });
    }

    // Delete transaction
    public boolean deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get summary
    public SummaryDTO getSummary() {
        BigDecimal totalIncome = transactionRepository.sumAmountByType(TransactionType.INCOME);
        BigDecimal totalExpense = transactionRepository.sumAmountByType(TransactionType.EXPENSE);
        return new SummaryDTO(totalIncome, totalExpense);
    }
}
