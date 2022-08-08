package com.ekizmaz.payment.repository;

import com.ekizmaz.payment.entity.Payment;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository  extends JpaRepository<Payment,Long> {

    Slice<Payment> findByUserId(Long id);
    Slice<Payment> findByTicketId(Long id);
}
