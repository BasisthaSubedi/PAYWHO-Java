package com.paywho.expensesplitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/create-trip")
    public String createTrip() {
        return "create-trip";
    }
    
    @GetMapping("/trip/{id}")
    public String tripDashboard(@PathVariable Long id) {
        return "trip-dashboard";
    }
}
