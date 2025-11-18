package com.paywho.expensesplitter.repository;

import com.paywho.expensesplitter.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    
    @Query("SELECT t FROM Trip t LEFT JOIN FETCH t.participants WHERE t.id = :id")
    Optional<Trip> findByIdWithParticipants(@Param("id") Long id);
    
    @Query("SELECT t FROM Trip t LEFT JOIN FETCH t.participants LEFT JOIN FETCH t.expenses WHERE t.id = :id")
    Optional<Trip> findByIdWithParticipantsAndExpenses(@Param("id") Long id);
    
    List<Trip> findAllByOrderByCreatedAtDesc();
}
