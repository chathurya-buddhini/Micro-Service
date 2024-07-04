package com.example.payment_service.service.util;


import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PaymentDTO fromPaymentEntity(PaymentEntity paymentEntity) {
        return mapper.map(paymentEntity, PaymentDTO.class);
    }

    public PaymentEntity toPaymentEntity(PaymentDTO paymentDTO) {
        return mapper.map(paymentDTO, PaymentEntity.class);
    }



}
