package com.example.finance.controller;

import com.example.finance.dto.SummaryDTO;
import com.example.finance.model.Transaction;
import com.example.finance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // GET /api/transactions?type=INCOME&startDate=2024-01-01&endDate=2024-12-31
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        List<Transaction> transactions = transactionService.getAllTransactions(type, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    // GET /api/transactions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/transactions
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction created = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/transactions/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/transactions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionService.deleteTransaction(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/transactions/summary
    @GetMapping("/summary")
    public ResponseEntity<SummaryDTO> getSummary() {
        SummaryDTO summary = transactionService.getSummary();
        return ResponseEntity.ok(summary);
    }
}
