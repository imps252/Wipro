package com.example.ticketreservation.service;

import com.example.ticketreservation.model.Ticket;
import com.example.ticketreservation.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        if (existingTicket.isPresent()) {
            Ticket ticket = existingTicket.get();
            ticket.setPassengerName(updatedTicket.getPassengerName());
            ticket.setSeatNumber(updatedTicket.getSeatNumber());
            ticket.setTravelDate(updatedTicket.getTravelDate());
            return ticketRepository.save(ticket);
        }
        return null; // Handle not found case properly in controller
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }  
}