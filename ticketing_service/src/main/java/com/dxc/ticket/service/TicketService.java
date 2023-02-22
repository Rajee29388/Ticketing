package com.dxc.ticket.service;

import java.util.List;
import java.util.Optional;

import com.dxc.ticket.dto.Ticketdto;
import com.dxc.ticket.entity.Ticket;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {

	// Save Ticket
	Mono<Ticket> saveTicket(Ticket ticket);

	//	Get Ticket List
	Flux<Ticket> fetchTicketList();
	
	// Get Ticket by TicketId
	Mono<Ticket> getTicket(Long ticketId);

	// Update Ticket
	Mono<Ticket> updateTicket(Ticket ticket,Long ticketId);

	// Delete Ticket by TicketId
	Mono<Void> deleteTicketById(Long ticketId);

	Mono<Ticket> updateTicketBookTime(long ticketId);
	
	//	Get available Ticket List
	Mono<List<Ticket>> fetchAvailableTicketList();
	
	Mono<Ticketdto> getTicketWithStatus(long ticketId);
	
}
