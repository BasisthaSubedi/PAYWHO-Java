package com.paywho.expensesplitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateExpenseRequest {
    
    @NotBlank(message = "Expense description is required")
    private String description;
    
    @NotNull(message = "Expense amount is required")
    @Positive(message = "Expense amount must be positive")
    private BigDecimal amount;
    
    @NotNull(message = "Trip ID is required")
    private Long tripId;
    
    @NotNull(message = "Payer ID is required")
    private Long payerId;
    
    // Constructors
    public CreateExpenseRequest() {}
    
    public CreateExpenseRequest(String description, BigDecimal amount, Long tripId, Long payerId) {
        this.description = description;
        this.amount = amount;
        this.tripId = tripId;
        this.payerId = payerId;
    }
    
    // Getters and Setters
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public Long getTripId() {
        return tripId;
    }
    
    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
    
    public Long getPayerId() {
        return payerId;
    }
    
    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }
}
