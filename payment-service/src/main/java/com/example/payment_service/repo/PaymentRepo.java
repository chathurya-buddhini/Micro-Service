package com.example.payment_service.repo;


import com.example.payment_service.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentEntity, String> {

}
