package com.example.ticket_service.service;



import com.example.ticket_service.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllTickets();

    TicketDTO getTicketById(String id);

    Boolean isTicketExit(String id);

    TicketDTO saveTicket (TicketDTO ticketDTO);

    void updateTicket(TicketDTO ticketDTO);

    void deleteTicket(String id);
}
