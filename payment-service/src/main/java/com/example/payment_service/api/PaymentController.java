package com.example.payment_service.api;

import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getAllPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping(path = "/{id}")
    public PaymentDTO getPaymentByID(@PathVariable("id") String id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO savePayment(@Valid @RequestBody PaymentDTO paymentDTO){
        return paymentService.savePayment(paymentDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePayment(@Valid @RequestBody PaymentDTO paymentDTO){
        paymentService.updatePayment(paymentDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable("id") String id){
        paymentService.deletePayment(id);
    }
}
