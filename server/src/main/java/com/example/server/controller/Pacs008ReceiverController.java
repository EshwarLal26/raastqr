package com.example.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.service.Pacs002QueueService;

@RestController
public class Pacs008ReceiverController {

    private static final Logger logger = LoggerFactory.getLogger(Pacs008ReceiverController.class);

    private final Pacs002QueueService pacs002QueueService;

    public Pacs008ReceiverController(Pacs002QueueService pacs002QueueService) {
        this.pacs002QueueService = pacs002QueueService;
    }

    @PostMapping(
            value = "/pacs008",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> receivePacs008(@RequestBody String pacs008Xml) {
        logger.info("Received pacs.008 XML message with {} characters", pacs008Xml.length());
        pacs002QueueService.enqueueAcceptedStatusForPacs008(pacs008Xml);
        return ResponseEntity.ok("pacs.008 received successfully");
    }
}
