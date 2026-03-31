package com.example.finance.repository;

import com.example.finance.model.Transaction;
import com.example.finance.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find by type
    List<Transaction> findByTypeOrderByDateDesc(TransactionType type);

    // Find by date range
    List<Transaction> findByDateBetweenOrderByDateDesc(LocalDate startDate, LocalDate endDate);

    // Find by type and date range
    List<Transaction> findByTypeAndDateBetweenOrderByDateDesc(
            TransactionType type, LocalDate startDate, LocalDate endDate);

    // Sum by type (for summary)
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type")
    BigDecimal sumAmountByType(@Param("type") TransactionType type);

    // Find all ordered by date desc
    List<Transaction> findAllByOrderByDateDesc();
}
