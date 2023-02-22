package com.dxc.ticket.repository;



import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.dxc.ticket.entity.Ticket;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, Long>{

}
