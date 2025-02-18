package org.example.mspayment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.mspayment.entity.Payment;
import org.example.mspayment.exception.PaymentNotFoundException;
import org.example.mspayment.model.dto.PaymentRequest;

import static org.example.mspayment.model.enums.PaymentStatus.*;
import static org.example.mspayment.model.mapper.PaymentMapper.*;

import org.example.mspayment.model.dto.PaymentResponse;
import org.example.mspayment.model.enums.ErrorMessage;
import org.example.mspayment.model.mapper.PaymentMapper;
import org.example.mspayment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentResponse pay(PaymentRequest paymentRequest) {
        Payment payment = PAYMENT_MAPPER.paymentRequestToPayment(paymentRequest);
        payment.setPaymentStatus(SUCCESS);

        return PAYMENT_MAPPER.paymentToPaymentResponse(paymentRepository.save(payment));
    }

    public PaymentResponse getPaymentByOrderId(Long id){
        return PAYMENT_MAPPER.paymentToPaymentResponse(findByOrderId(id));
    }

    private Payment findByOrderId(Long id){
        return paymentRepository
                .findByOrderId(id)
                .orElseThrow(()->new PaymentNotFoundException(String.format(
                        ErrorMessage.PAYMENT_NOT_FOUND.getMessage(), id
                )));
    }

}
