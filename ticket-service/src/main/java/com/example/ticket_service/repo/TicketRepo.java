package com.example.ticket_service.repo;


import com.example.ticket_service.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<TicketEntity, String> {

}
