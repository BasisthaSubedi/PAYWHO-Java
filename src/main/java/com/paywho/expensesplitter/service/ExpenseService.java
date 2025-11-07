package com.paywho.expensesplitter.service;

import com.paywho.expensesplitter.model.Expense;
import com.paywho.expensesplitter.model.Participant;
import com.paywho.expensesplitter.model.Trip;
import com.paywho.expensesplitter.repository.ExpenseRepository;
import com.paywho.expensesplitter.repository.ParticipantRepository;
import com.paywho.expensesplitter.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseService {
    
    private final ExpenseRepository expenseRepository;
    private final ParticipantRepository participantRepository;
    private final TripRepository tripRepository;
    
    public ExpenseService(ExpenseRepository expenseRepository, ParticipantRepository participantRepository, TripRepository tripRepository) {
        this.expenseRepository = expenseRepository;
        this.participantRepository = participantRepository;
        this.tripRepository = tripRepository;
    }
    
    public List<Expense> getExpensesByTripId(Long tripId) {
        return expenseRepository.findByTripIdWithPayer(tripId);
    }
    
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    public Expense createExpenseWithRelations(String description, BigDecimal amount, Long tripId, Long payerId) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found"));
        
        Participant payer = participantRepository.findById(payerId)
            .orElseThrow(() -> new RuntimeException("Participant not found"));
        
        Expense expense = new Expense(description, amount, trip, payer);
        return expenseRepository.save(expense);
    }
    
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }
    
    public Double getTotalExpenseAmount(Long tripId) {
        Double total = expenseRepository.getTotalExpenseAmountByTripId(tripId);
        return total != null ? total : 0.0;
    }
    
    public Map<String, Object> getTripSummary(Long tripId) {
        List<Expense> expenses = getExpensesByTripId(tripId);
        List<Participant> participants = participantRepository.findByTripId(tripId);
        
        Double totalAmount = getTotalExpenseAmount(tripId);
        Double perPersonAmount = participants.isEmpty() ? 0.0 : totalAmount / participants.size();
        
        // Calculate individual balances
        Map<String, Double> balances = participants.stream()
            .collect(Collectors.toMap(
                p -> p.getName(),
                p -> {
                    Double paid = expenses.stream()
                        .filter(e -> e.getPayer().getId().equals(p.getId()))
                        .mapToDouble(e -> e.getAmount().doubleValue())
                        .sum();
                    return paid - perPersonAmount;
                }
            ));
        
        return Map.of(
            "totalAmount", totalAmount,
            "perPersonAmount", perPersonAmount,
            "participantCount", participants.size(),
            "expenseCount", expenses.size(),
            "balances", balances
        );
    }
}
