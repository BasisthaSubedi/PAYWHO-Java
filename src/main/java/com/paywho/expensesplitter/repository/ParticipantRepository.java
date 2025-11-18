package com.paywho.expensesplitter.repository;

import com.paywho.expensesplitter.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
    List<Participant> findByTripId(Long tripId);
    
    @Query("SELECT p FROM Participant p WHERE p.trip.id = :tripId")
    List<Participant> findByTripIdWithTrip(@Param("tripId") Long tripId);
    
    boolean existsByTripIdAndName(Long tripId, String name);
}
