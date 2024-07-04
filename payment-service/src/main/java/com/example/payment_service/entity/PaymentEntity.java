package com.example.payment_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private String id;

    private String ticketid;

    private double amount;
    @CreationTimestamp
    @Column(name = "purchased_date")
    private Date purchasedDate;

    @Column(name = "type")
    private String paymentType;



}


