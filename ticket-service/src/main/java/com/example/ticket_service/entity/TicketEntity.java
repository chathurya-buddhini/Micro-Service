package com.example.ticket_service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "user_nic", nullable = false)
    private String user_nic;

    @Column(name = "vehicleId", nullable = false)
    private String vehicleId;

    @Column(name = "total")
    private double totalPrice;

    @CreationTimestamp
    @Column(name = "issued_date", nullable = false)
    private Date issuedDate;

    @Column(name = "entres")
    private String entres;

    @Column(name = "exit_name")
    private String exit_name;

    @Column(name = "departureTime")
    private Time departureTime;

    @Column(name = "arrivalTime")
    private Time arrivalTime;

    @Column(name = "status")
    private String status;



}
