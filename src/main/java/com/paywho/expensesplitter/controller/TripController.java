package com.paywho.expensesplitter.controller;

import com.paywho.expensesplitter.dto.CreateTripRequest;
import com.paywho.expensesplitter.model.Trip;
import com.paywho.expensesplitter.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "*")
public class TripController {
    
    private final TripService tripService;
    
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        return tripService.getTripById(id)
            .map(trip -> ResponseEntity.ok(trip))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Trip> createTrip(@Valid @RequestBody CreateTripRequest request) {
        Trip createdTrip = tripService.createTripWithParticipants(request.getName(), request.getParticipants());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @Valid @RequestBody Trip trip) {
        if (!tripService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        trip.setId(id);
        Trip updatedTrip = tripService.updateTrip(trip);
        return ResponseEntity.ok(updatedTrip);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        if (!tripService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("message", "PAYWHO API is running!"));
    }
}
