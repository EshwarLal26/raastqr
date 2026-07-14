package com.example.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.service.Pacs002QueueService;

@RestController
public class Pacs002PollingController {

    private final Pacs002QueueService pacs002QueueService;

    public Pacs002PollingController(Pacs002QueueService pacs002QueueService) {
        this.pacs002QueueService = pacs002QueueService;
    }

    @GetMapping(value = {"/pacs002/messages", "/api/pacs002/messages"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> pollPacs002Messages() {
        return pacs002QueueService.drainPendingMessages();
    }

    @GetMapping(value = {"/pacs002/messages/{txId}", "/api/pacs002/messages/{txId}"}, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getGeneratedPacs002(@PathVariable String txId) {
        return pacs002QueueService.findGeneratedMessageByTxId(txId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}