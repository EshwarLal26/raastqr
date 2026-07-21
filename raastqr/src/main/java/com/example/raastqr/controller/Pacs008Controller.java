package com.example.raastqr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.dto.PaymentSubmitResponse;
import com.example.raastqr.dto.SubmissionTokenResponse;
import com.example.raastqr.service.Pacs008ClientService;
import com.example.raastqr.service.SubmissionTokenService;

@RestController
@RequestMapping("/api")
public class Pacs008Controller {

    private final Pacs008ClientService pacs008ClientService;
    private final SubmissionTokenService submissionTokenService;

    public Pacs008Controller(Pacs008ClientService pacs008ClientService,
                             SubmissionTokenService submissionTokenService) {
        this.pacs008ClientService = pacs008ClientService;
        this.submissionTokenService = submissionTokenService;
    }

    @GetMapping("/pacs008/token")
    public ResponseEntity<SubmissionTokenResponse> issueSubmissionToken() {
        SubmissionTokenService.IssuedToken issuedToken = submissionTokenService.issueToken();
        return ResponseEntity.ok(new SubmissionTokenResponse(
                issuedToken.getToken(),
                issuedToken.getExpiresInSeconds()
        ));
    }

    @PostMapping("/pacs008")
    public ResponseEntity<String> generateXml(@RequestBody Pacs008Request request) throws Exception {
        return ResponseEntity.ok(pacs008ClientService.buildPacs008Xml(request));
    }

    @PostMapping("/pacs008/send")
    public ResponseEntity<PaymentSubmitResponse> sendPacs008(@RequestBody Pacs008Request request) throws Exception {
        String submissionToken = resolveSubmissionToken(request);

        PaymentStatusResponse status = pacs008ClientService.sendAndTrackPacs008(request);
        return ResponseEntity.ok(toSubmitResponse(status, submissionToken));
    }

    private String resolveSubmissionToken(Pacs008Request request) {
        String submissionToken = request.getSubmissionToken();
        if (submissionToken == null || submissionToken.isBlank()) {
            SubmissionTokenService.IssuedToken issuedToken = submissionTokenService.issueToken();
            request.setSubmissionToken(issuedToken.getToken());
            return issuedToken.getToken();
        }

        if (!submissionTokenService.consumeToken(submissionToken)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Invalid, expired, or already-used submission token"
            );
        }

        return submissionToken;
    }

    private PaymentSubmitResponse toSubmitResponse(PaymentStatusResponse status, String submissionToken) {
        PaymentSubmitResponse response = new PaymentSubmitResponse();
        response.setSuccess("SENT_TO_HOST".equals(status.getTransportStatus()));
        response.setMessage(resolveSubmitMessage(status));
        response.setSubmissionToken(submissionToken);
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


