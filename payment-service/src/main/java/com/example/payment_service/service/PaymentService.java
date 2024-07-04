package com.example.payment_service.service;


import com.example.payment_service.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getAllPayments();

    PaymentDTO getPaymentById(String id);

    PaymentDTO savePayment (PaymentDTO paymentDTO);

    void updatePayment(PaymentDTO paymentDTO);

    void deletePayment(String id);
}
