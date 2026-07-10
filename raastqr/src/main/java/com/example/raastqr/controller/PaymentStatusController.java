package com.example.raastqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.service.PaymentTrackingService;

@RestController
@RequestMapping("/api/payments")
public class PaymentStatusController {

    private final PaymentTrackingService paymentTrackingService;

    public PaymentStatusController(PaymentTrackingService paymentTrackingService) {
        this.paymentTrackingService = paymentTrackingService;
    }

    @GetMapping("/{txId}/status")
    public ResponseEntity<PaymentStatusResponse> getStatus(@PathVariable String txId) {
        return paymentTrackingService.findByTxId(txId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
