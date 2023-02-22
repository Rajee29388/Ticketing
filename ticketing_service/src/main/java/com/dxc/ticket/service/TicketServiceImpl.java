package com.dxc.ticket.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.ticket.dto.Ticketdto;
import com.dxc.ticket.entity.Ticket;
import com.dxc.ticket.exception.ResourceNotFoundException;
import com.dxc.ticket.repository.TicketRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Mono<Ticket> saveTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Flux<Ticket> fetchTicketList() {
		return ticketRepository.findAll();
	}

	@Override
	public Mono<Ticket> getTicket(Long ticketId) {
		return ticketRepository.findById(ticketId);
	}

	@Override
	public Mono<Ticket> updateTicket(Ticket ticket, Long ticketId) {
		
		return ticketRepository.findById(ticketId)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("No product found for productId: " + ticketId)))
				.map(p -> {
					p.setTicketName(ticket.getTicketName());
					p.setPrice(ticket.getPrice());
					p.setStatus(ticket.getStatus());
					p.setCreatedDate(ticket.getCreatedDate());
					p.setStatus(ticket.getStatus());

					return p;
				}).flatMap(p -> ticketRepository.save(p));
		
				
				
	}

	@Override
	public Mono<Void> deleteTicketById(Long ticketId) {
		
		return ticketRepository.deleteById(ticketId);
	}

	@Override
	public Mono<Ticket> updateTicketBookTime(long ticketId) {
			
		return ticketRepository.findById(ticketId)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("No product found for productId: " + ticketId)))
				.map(p -> {p.setLockedStartTime(LocalDateTime.now());
					return p;
				}).flatMap(p -> ticketRepository.save(p));
	}

	@Override
	public Mono<List<Ticket>> fetchAvailableTicketList() {
		return ticketRepository.findAll()
				.filter(t -> t.getStatus().equalsIgnoreCase("Available")).collect(Collectors.toList());
		
	}

	@Override
	public Mono<Ticketdto> getTicketWithStatus(long ticketId) {
		
		return ticketRepository.findById(ticketId)
				.switchIfEmpty(
						Mono.error(new ResourceNotFoundException("No product found for productId: " + ticketId)))
				.map(p -> {
					
				Ticketdto ticketdto = new Ticketdto();
				ticketdto.setTicketId(p.getTicketId());
				ticketdto.setTicketName(p.getTicketName());
				ticketdto.setPrice(p.getPrice());
				
				if(!p.getStatus().equals("Booked")) {
					LocalDateTime date = LocalDateTime.now();
					LocalDateTime lockdate = p.getLockedStartTime();
					if (lockdate == null) {
						ticketdto.setStatus("Available");
					} else {
						long difference_In_Minutes = ChronoUnit.MINUTES.between(lockdate, date);
									
						if (difference_In_Minutes > 15) {
							ticketdto.setStatus("Available");
			
						} else {
							ticketdto.setStatus("Booked");
						}
					}
				} else {
					ticketdto.setStatus(p.getStatus());
				}
				return ticketdto;
					});
				
		
	}

	
	

}
