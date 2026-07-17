package com.example.server.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.service.Pacs004QueueService;

@RestController
public class Pacs004PollingController {

    private final Pacs004QueueService pacs004QueueService;

    public Pacs004PollingController(Pacs004QueueService pacs004QueueService) {
        this.pacs004QueueService = pacs004QueueService;
    }

    @GetMapping(value = {"/pacs004/message", "/api/pacs004/message"}, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> pollPacs004Message() {
        return pacs004QueueService.poll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
