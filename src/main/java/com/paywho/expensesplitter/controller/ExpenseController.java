package com.paywho.expensesplitter.controller;

import com.paywho.expensesplitter.dto.CreateExpenseRequest;
import com.paywho.expensesplitter.dto.ExpenseResponse;
import com.paywho.expensesplitter.model.Expense;
import com.paywho.expensesplitter.model.Participant;
import com.paywho.expensesplitter.service.ExpenseService;
import com.paywho.expensesplitter.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExpenseController {
    
    private final ExpenseService expenseService;
    private final ParticipantService participantService;
    
    public ExpenseController(ExpenseService expenseService, ParticipantService participantService) {
        this.expenseService = expenseService;
        this.participantService = participantService;
    }
    
    @GetMapping("/expenses/trip/{tripId}")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByTrip(@PathVariable Long tripId) {
        List<Expense> expenses = expenseService.getExpensesByTripId(tripId);
        List<ExpenseResponse> expenseResponses = expenses.stream()
            .map(expense -> new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getPayer().getName(),
                expense.getCreatedAt()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(expenseResponses);
    }
    
    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody CreateExpenseRequest request) {
        Expense expense = expenseService.createExpenseWithRelations(
            request.getDescription(),
            request.getAmount(),
            request.getTripId(),
            request.getPayerId()
        );
        ExpenseResponse response = new ExpenseResponse(
            expense.getId(),
            expense.getDescription(),
            expense.getAmount(),
            expense.getPayer().getName(),
            expense.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/summary/{tripId}")
    public ResponseEntity<Map<String, Object>> getTripSummary(@PathVariable Long tripId) {
        Map<String, Object> summary = expenseService.getTripSummary(tripId);
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/participants/trip/{tripId}")
    public ResponseEntity<List<Participant>> getParticipantsByTrip(@PathVariable Long tripId) {
        List<Participant> participants = participantService.getParticipantsByTripId(tripId);
        return ResponseEntity.ok(participants);
    }
    
    @PostMapping("/participants")
    public ResponseEntity<Participant> createParticipant(@Valid @RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
    }
}
