package com.example.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.service.Pacs002QueueService;
import com.example.server.service.Pacs004QueueService;
import com.example.server.service.Pacs004Service;

@RestController
public class Pacs008ReceiverController {

    private static final Logger logger = LoggerFactory.getLogger(Pacs008ReceiverController.class);

    private final Pacs002QueueService pacs002QueueService;
    private final Pacs004QueueService pacs004QueueService;
    private final Pacs004Service pacs004Service;

    public Pacs008ReceiverController(Pacs002QueueService pacs002QueueService,
                                     Pacs004QueueService pacs004QueueService,
                                     Pacs004Service pacs004Service) {
        this.pacs002QueueService = pacs002QueueService;
        this.pacs004QueueService = pacs004QueueService;
        this.pacs004Service = pacs004Service;
    }

    @PostMapping(
            value = "/pacs008",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> receivePacs008(@RequestBody String pacs008Xml) {
        logger.info("Received pacs.008 XML message with {} characters", pacs008Xml.length());
        pacs002QueueService.enqueueAcceptedStatusForPacs008(pacs008Xml);

        if (shouldReturnPayment(pacs008Xml)) {
            String pacs004Xml = pacs004Service.buildPacs004FromPacs008(
                    pacs008Xml,
                    "AC04",
                    "Beneficiary account closed. Original pacs.008 returned."
            );
            pacs004QueueService.add(pacs004Xml);
            logger.info("Generated pacs.004 return message for returned pacs.008");
        }

        return ResponseEntity.ok("pacs.008 received successfully");
    }

    private boolean shouldReturnPayment(String pacs008Xml) {
        return pacs008Xml.contains("RETURN_TEST")
                || pacs008Xml.contains("<InstrInf>RETURN")
                || pacs008Xml.contains("<Ustrd>RETURN");
    }
}
