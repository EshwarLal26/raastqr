package com.example.server.service;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

@Service
public class Pacs004QueueService {

    private final Queue<String> pacs004Messages = new ConcurrentLinkedQueue<>();

    public void add(String pacs004Xml) {
        pacs004Messages.add(pacs004Xml);
    }

    public Optional<String> poll() {
        return Optional.ofNullable(pacs004Messages.poll());
    }
}
