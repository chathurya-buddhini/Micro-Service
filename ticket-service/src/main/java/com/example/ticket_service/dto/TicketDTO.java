package com.example.ticket_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {

    private String ticketId;

    @NotBlank(message = " vehicle can not be null")
    private String vehicleId;

    @NotBlank(message = "user nic can not be null")
    private String user_nic;

    @DecimalMin(value = "0.0", inclusive = false, message = "total price must be greater than zero")
    private double totalPrice;

    private Date issuedDate;

    @NotBlank(message = "exit point can not be null")
    private String entres;

    @NotBlank(message = " can not be null")
    private String exit_name;

    @JsonFormat(pattern = "yyyyMMdd")
    private Time departureTime;

    @JsonFormat(pattern = "yyyyMMdd")
    private Time arrivalTime;

    private String status;


}
//    {
//       "ticketId": "TI001",
//        "vehicleId": "V001",
//            "user-nic": "U001",
//            "totalPrice": 9500.00,
//            "issuedDate": "",
//            "entres": "kaduvela",
//            "exit_name": "Mathara",
//            "departureTime": "10:00:00",
//            "arrivalTime": "12:00:00",
//            "status": "Active"
//    }
