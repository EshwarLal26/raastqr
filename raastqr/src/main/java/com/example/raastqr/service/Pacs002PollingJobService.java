package com.example.raastqr.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Pacs002PollingJobService {

    private final SbpStatusClientService sbpStatusClientService;
    private final Pacs002Service pacs002Service;
    private final Pacs004Service pacs004Service;
    private final PaymentTrackingService paymentTrackingService;

    public Pacs002PollingJobService(SbpStatusClientService sbpStatusClientService,
                                    Pacs002Service pacs002Service,
                                    Pacs004Service pacs004Service,
                                    PaymentTrackingService paymentTrackingService) {
        this.sbpStatusClientService = sbpStatusClientService;
        this.pacs002Service = pacs002Service;
        this.pacs004Service = pacs004Service;
        this.paymentTrackingService = paymentTrackingService;
    }

    @Scheduled(
            fixedDelayString = "${app.sbp-poll.fixed-delay-ms:30000}",
            initialDelayString = "${app.sbp-poll.initial-delay-ms:10000}"
    )
    public void pollStatuses() {
        List<String> messages = sbpStatusClientService.fetchPacs002Messages();
        for (String xml : messages) {
            PaymentTrackingService.Pacs002CallbackData callbackData = pacs002Service.parseIncomingPacs002(xml);
            paymentTrackingService.updateFromPacs002(callbackData, xml, "SBP_POLL");
        }

        sbpStatusClientService.fetchPacs004Message().ifPresent(xml -> {
            PaymentTrackingService.Pacs004CallbackData callbackData = pacs004Service.parseIncomingPacs004(xml);
            paymentTrackingService.updateFromPacs004(callbackData, xml, "SBP_PACS004_POLL");
        });
    }
}
