package com.dxc.ticket.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;





@Table("Ticket")
public class Ticket {

	@Id
	@Column("ticketId")
	private Long ticketId;
	
	@Column("ticketName")
	private String ticketName;
	
	@Column("price")
	private String price;
	
	@Column("status")
	private String status;
	
	@Column("CreatedDate")
	private LocalDateTime CreatedDate;
	
	@Column("lockedStartTime")
	private LocalDateTime lockedStartTime;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public LocalDateTime getLockedStartTime() {
		return lockedStartTime;
	}
	public void setLockedStartTime(LocalDateTime lockedStartTime) {
		this.lockedStartTime = lockedStartTime;
	}

	
}
