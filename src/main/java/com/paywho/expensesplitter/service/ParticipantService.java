package com.paywho.expensesplitter.service;

import com.paywho.expensesplitter.model.Participant;
import com.paywho.expensesplitter.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipantService {
    
    private final ParticipantRepository participantRepository;
    
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
    
    public List<Participant> getParticipantsByTripId(Long tripId) {
        return participantRepository.findByTripId(tripId);
    }
    
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }
    
    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }
    
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
    
    public boolean existsByTripIdAndName(Long tripId, String name) {
        return participantRepository.existsByTripIdAndName(tripId, name);
    }
}
