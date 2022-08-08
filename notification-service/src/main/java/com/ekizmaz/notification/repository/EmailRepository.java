package com.ekizmaz.notification.repository;


import com.ekizmaz.notification.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
