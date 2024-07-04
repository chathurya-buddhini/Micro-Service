package com.example.payment_service.service.impl;

import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.entity.PaymentEntity;
import com.example.payment_service.repo.PaymentRepo;
import com.example.payment_service.service.PaymentService;
import com.example.payment_service.service.exception.DuplicateRecordException;
import com.example.payment_service.service.exception.NotFoundException;
import com.example.payment_service.service.util.Transformer;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<PaymentDTO> getAllPayments() {

        List<PaymentEntity> payments = paymentRepo.findAll();

        if (payments.isEmpty()) {
            throw new NotFoundException("No Payments Exist");
        }
        return payments.stream().map(paymentEntity -> transformer.fromPaymentEntity(paymentEntity)).toList();
    }

    @Override
    public PaymentDTO getPaymentById(String id) {

        if (!paymentRepo.existsById(id)) {
            throw new NotFoundException("Payment nic : " + id + " doesn't exist");
        }
        return transformer.fromPaymentEntity(paymentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment nic : " + id + " doesn't exist")));
    }

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        if (paymentRepo.existsById(paymentDTO.getId())) {
            throw new DuplicateRecordException("payment id : " + paymentDTO.getId() + " already exist");
        }
        return transformer.fromPaymentEntity(paymentRepo.save(transformer.toPaymentEntity(paymentDTO)));

//        Boolean isTicketNumExist = restTemplate.getForObject("http://ticket-service/ticket/isExit/" + paymentDTO.getTicketid(), Boolean.class);
//        if(Boolean.FALSE.equals(isTicketNumExist)){
//            throw new NotFoundException("Ticket Num : "+paymentDTO.getTicketid()+" doesn't exist");
//        }
//
//        if (paymentDTO.getId() == null || paymentDTO.getId().isEmpty()) {
//            paymentDTO.setId(UUID.randomUUID().toString());
//        }
//
//        if (paymentRepo.existsById(paymentDTO.getId())) {
//            throw new DuplicateRecordException("Payment id : " + paymentDTO.getId() + " already exist");
//        }
//
//
//        return transformer.fromPaymentEntity(paymentRepo.save(transformer.toPaymentEntity(paymentDTO)));
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO) {
        if (!paymentRepo.existsById(paymentDTO.getId())) {
            throw new NotFoundException("paymant id : " + paymentDTO.getId() + " doesn't exist");
        }
        paymentRepo.save(transformer.toPaymentEntity(paymentDTO));
//        if (!paymentRepo.existsById(paymentDTO.getId())) {
//            throw new NotFoundException("Payment id : " + paymentDTO.getId() + " doesn't exist");
//        }
//
//        PaymentEntity payment = paymentRepo.findById(paymentDTO.getId()).orElseThrow(
//                () -> new NotFoundException("PaymentId : "+paymentDTO.getId()+" doesn't exist")
//        );
//
//        payment.setPurchasedDate(payment.getPurchasedDate());
//        paymentRepo.save(payment);
    }

    @Override
    public void deletePayment(String paymentId) {
        if (!paymentRepo.existsById(paymentId)) {
            throw new NotFoundException("Payment id : " + paymentId + " doesn't exist");
        }
        paymentRepo.deleteById(paymentId);
    }

}
