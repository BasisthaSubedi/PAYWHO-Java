package com.paywho.expensesplitter.service;

import com.paywho.expensesplitter.model.Participant;
import com.paywho.expensesplitter.model.Trip;
import com.paywho.expensesplitter.repository.ParticipantRepository;
import com.paywho.expensesplitter.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TripService {
    
    private final TripRepository tripRepository;
    private final ParticipantRepository participantRepository;
    
    public TripService(TripRepository tripRepository, ParticipantRepository participantRepository) {
        this.tripRepository = tripRepository;
        this.participantRepository = participantRepository;
    }
    
    public List<Trip> getAllTrips() {
        return tripRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findByIdWithParticipants(id);
    }
    
    public Optional<Trip> getTripWithExpenses(Long id) {
        return tripRepository.findByIdWithParticipantsAndExpenses(id);
    }
    
    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }
    
    public Trip createTripWithParticipants(String tripName, List<String> participantNames) {
        Trip trip = new Trip(tripName);
        Trip savedTrip = tripRepository.save(trip);
        
        for (String participantName : participantNames) {
            if (participantName != null && !participantName.trim().isEmpty()) {
                Participant participant = new Participant(participantName.trim(), savedTrip);
                participantRepository.save(participant);
            }
        }
        
        return savedTrip;
    }
    
    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }
    
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
    }
}
