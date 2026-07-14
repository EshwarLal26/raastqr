package com.example.raastqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.dto.PaymentSubmitResponse;
import com.example.raastqr.service.Pacs008ClientService;

@RestController
@RequestMapping("/api")
public class Pacs008Controller {

    private final Pacs008ClientService pacs008ClientService;

    public Pacs008Controller(Pacs008ClientService pacs008ClientService) {
        this.pacs008ClientService = pacs008ClientService;
    }

    @PostMapping("/pacs008")
    public ResponseEntity<String> generateXml(@RequestBody Pacs008Request request) throws Exception {
        return ResponseEntity.ok(pacs008ClientService.buildPacs008Xml(request));
    }

    @PostMapping("/pacs008/send")
    public ResponseEntity<PaymentSubmitResponse> sendPacs008(@RequestBody Pacs008Request request) throws Exception {
        PaymentStatusResponse status = pacs008ClientService.sendAndTrackPacs008(request);
        return ResponseEntity.ok(toSubmitResponse(status));
    }

    private PaymentSubmitResponse toSubmitResponse(PaymentStatusResponse status) {
        PaymentSubmitResponse response = new PaymentSubmitResponse();
        response.setSuccess("SENT_TO_HOST".equals(status.getTransportStatus()));
        response.setMessage(resolveSubmitMessage(status));
        response.setTxId(status.getTxId());
        response.setMsgId(status.getMsgId());
        response.setInstrId(status.getInstrId());
        response.setEndToEndId(status.getEndToEndId());
        response.setRequestId(status.getRequestId());
        response.setTraceReference(status.getTraceReference());
        response.setStatus(status.getFinalStatus());
        response.setNextAction("Check payment status using GET /api/payments/" + status.getTxId() + "/status");
        return response;
    }

    private String resolveSubmitMessage(PaymentStatusResponse status) {
        if ("SENT_TO_HOST".equals(status.getTransportStatus())) {
            return "Payment instruction sent successfully. Awaiting pacs.002 status report.";
        }
        if ("HOST_REJECT".equals(status.getTransportStatus())) {
            return "Payment instruction was rejected by host.";
        }
        return "Payment instruction created.";
    }
}