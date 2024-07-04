package com.example.ticket_service.service.impl;

import com.example.ticket_service.dto.TicketDTO;
import com.example.ticket_service.entity.TicketEntity;
import com.example.ticket_service.repo.TicketRepo;
import com.example.ticket_service.service.TicketService;
import com.example.ticket_service.service.exception.DuplicateRecordException;
import com.example.ticket_service.service.exception.NotFoundException;
import com.example.ticket_service.service.util.Transformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<TicketDTO> getAllTickets() {

        List<TicketEntity> tickets = ticketRepo.findAll();

        if (tickets.isEmpty()) {
            throw new NotFoundException("No Tickets Exist");
        }
        return tickets.stream().map(ticketEntity -> transformer.fromTicketEntity(ticketEntity)).toList();
    }

    @Override
    public TicketDTO getTicketById(String id) {

        if (!ticketRepo.existsById(id)) {
            throw new NotFoundException("Ticket nic : " + id + " doesn't exist");
        }
        return transformer.fromTicketEntity(ticketRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket nic : " + id + " doesn't exist")));
    }

    @Override
    public Boolean isTicketExit(String id) {
        return ticketRepo.existsById(id);
    }

    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {

        if (ticketRepo.existsById(String.valueOf(ticketDTO.getTicketId()))) {
            throw new DuplicateRecordException("Ticket id : " + ticketDTO.getTicketId() + " already exist");
        }

        Boolean isUserExit = restTemplate.getForObject("http://user-service/user/isExit/" + ticketDTO.getUser_nic(), Boolean.class);
        if(Boolean.FALSE.equals(isUserExit)){
            throw new NotFoundException("User Nic : "+ticketDTO.getUser_nic()+" doesn't exist");
        }

        if (ticketDTO.getTicketId() == null || ticketDTO.getTicketId().isEmpty()) {
            ticketDTO.setTicketId(UUID.randomUUID().toString());
        }

        return transformer.fromTicketEntity(ticketRepo.save(transformer.toTicketEntity(ticketDTO)));
    }

    @Override
    public void updateTicket(TicketDTO ticketDTO) {
        if (!ticketRepo.existsById(ticketDTO.getTicketId())) {
            throw new NotFoundException("Ticket id : " + ticketDTO.getTicketId() + " doesn't exist");
        }

        TicketEntity ticketData = ticketRepo.findById(ticketDTO.getTicketId()).orElseThrow(
                () -> new NotFoundException("Ticket Id : "+ticketDTO.getTicketId()+" doesn't exist")
        );
        TicketEntity ticketEntity = transformer.toTicketEntity(ticketDTO);
        ticketEntity.setIssuedDate(ticketData.getIssuedDate());
        ticketRepo.save(ticketEntity);
    }

    @Override
    public void deleteTicket(String ticketId) {
        if (!ticketRepo.existsById(ticketId)) {
            throw new NotFoundException("Ticket id : " + ticketId + " doesn't exist");
        }
        ticketRepo.deleteById(ticketId);
    }

}
