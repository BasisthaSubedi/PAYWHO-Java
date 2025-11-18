package com.paywho.expensesplitter.repository;

import com.paywho.expensesplitter.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    List<Expense> findByTripIdOrderByCreatedAtDesc(Long tripId);
    
    @Query("SELECT e FROM Expense e LEFT JOIN FETCH e.payer WHERE e.trip.id = :tripId ORDER BY e.createdAt DESC")
    List<Expense> findByTripIdWithPayer(@Param("tripId") Long tripId);
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.trip.id = :tripId")
    Double getTotalExpenseAmountByTripId(@Param("tripId") Long tripId);
}
