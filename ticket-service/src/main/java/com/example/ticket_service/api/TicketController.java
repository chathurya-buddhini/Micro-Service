package com.example.ticket_service.api;

import com.example.ticket_service.dto.TicketDTO;
import com.example.ticket_service.service.TicketService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getAllTicket() {
        return ticketService.getAllTickets();
    }

    @GetMapping(path = "/{id}")
    public TicketDTO getTicketByID(@PathVariable("id") String id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping(path = "isExit/{id}")
    public Boolean isTicketExit(@PathVariable("id") String id){
        return ticketService.isTicketExit(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO saveTicket(@Valid @RequestBody TicketDTO ticketDTO){
        return ticketService.saveTicket(ticketDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTicket(@Valid @RequestBody TicketDTO ticketDTO){
        ticketService.updateTicket(ticketDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") String id){
        ticketService.deleteTicket(id);
    }
}
