package com.ekizmaz.notification.repository;

import com.ekizmaz.notification.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, Integer> {
}
