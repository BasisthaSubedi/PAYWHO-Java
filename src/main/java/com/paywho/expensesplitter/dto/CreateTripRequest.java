package com.paywho.expensesplitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateTripRequest {
    
    @NotBlank(message = "Trip name is required")
    private String name;
    
    @NotEmpty(message = "At least one participant is required")
    private List<String> participants;
    
    // Constructors
    public CreateTripRequest() {}
    
    public CreateTripRequest(String name, List<String> participants) {
        this.name = name;
        this.participants = participants;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getParticipants() {
        return participants;
    }
    
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
