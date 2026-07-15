package com.example.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.entity.Pacs002MessageEntity;

public interface Pacs002MessageRepository extends JpaRepository<Pacs002MessageEntity, Long> {

    List<Pacs002MessageEntity> findByStatusOrderByCreatedAtAsc(String status);

    Optional<Pacs002MessageEntity> findFirstByTxIdOrderByCreatedAtDesc(String txId);
}