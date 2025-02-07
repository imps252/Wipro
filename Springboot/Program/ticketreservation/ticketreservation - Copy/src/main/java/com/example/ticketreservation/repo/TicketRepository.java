package com.example.ticketreservation.repo;

import com.example.ticketreservation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
