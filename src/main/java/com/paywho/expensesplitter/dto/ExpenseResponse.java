package com.paywho.expensesplitter.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseResponse {
    
    private Long id;
    private String description;
    private BigDecimal amount;
    private String payerName;
    private LocalDateTime createdAt;
    
    // Constructors
    public ExpenseResponse() {}
    
    public ExpenseResponse(Long id, String description, BigDecimal amount, String payerName, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.payerName = payerName;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public String getPayerName() {
        return payerName;
    }
    
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
