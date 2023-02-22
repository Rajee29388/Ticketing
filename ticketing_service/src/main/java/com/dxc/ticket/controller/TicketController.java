package com.dxc.ticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.ticket.dto.Ticketdto;
import com.dxc.ticket.entity.Ticket;
import com.dxc.ticket.service.TicketService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	// create new Ticket
	@PostMapping("")
	public Mono<Ticket> saveTicket(@RequestBody Ticket ticket) {
		return ticketService.saveTicket(ticket);

	}

	// Fetch Ticket By Ticket Id
	@GetMapping("/{Id}")
	public Mono<Ticket> fetchTicketDetail(@PathVariable("Id") Long Id) {
		return ticketService.getTicket(Id);
	}

	// Get tickets
	@GetMapping("")
	public Flux<Ticket> fetchTicketList() {
		return ticketService.fetchTicketList();
	}

	// Get available tickets
	@GetMapping("/available")
	public Mono<List<Ticket>> fetchAvailableTicketList() {
		return ticketService.fetchAvailableTicketList();
		
	}

	// Edit Ticket Details
	@PutMapping("{Id}")
	public Mono<Ticket> updateTicket(@PathVariable long Id, @RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticket, Id);
		
	}

	// Update lockedStartTime
	@PatchMapping("{id}")
	public Mono<Ticket> updateTicketLockedTime(@PathVariable long id) {

		return ticketService.updateTicketBookTime(id);
		
	}

	// To get the details on ticket availability
	@GetMapping("/openTicket/{id}")
	public Mono<Ticketdto> fetchTicketLockedTime(@PathVariable long id) {
		return ticketService.getTicketWithStatus(id);

	}

}
