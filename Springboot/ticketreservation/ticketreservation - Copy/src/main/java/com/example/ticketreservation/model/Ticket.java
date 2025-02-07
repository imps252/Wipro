package com.example.ticketreservation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passengerName;
    private String seatNumber;
    private String travelDate;

    // Getters and Setters
    public Long getId() { 
    	return id; 
    	}
    public void setId(Long id) { 
    	this.id = id; 
    	}
    public String getPassengerName() {
    	return passengerName; 
    	}
    public void setPassengerName(String passengerName) {
    	this.passengerName = passengerName; 
    	}
    public String getSeatNumber() { 
    	return seatNumber; 
    	}
    public void setSeatNumber(String seatNumber) {
    	this.seatNumber = seatNumber; 
    	}
    public String getTravelDate() {
    	return travelDate; 
    	}
    public void setTravelDate(String travelDate) {
    	this.travelDate = travelDate; 
    	}
}