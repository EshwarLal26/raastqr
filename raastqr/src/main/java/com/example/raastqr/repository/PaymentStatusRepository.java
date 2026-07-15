package com.example.raastqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raastqr.entity.PaymentStatusEntity;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, String> {
}