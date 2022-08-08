package com.ekizmaz.payment.controller;

import com.ekizmaz.payment.dto.PaymentDto;
import com.ekizmaz.payment.dto.PaymentSaveDto;
import com.ekizmaz.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paymentService.get(id));
    }
    @PostMapping
    public ResponseEntity post(@RequestBody PaymentSaveDto paymentSaveDto) {
        try {
            return ResponseEntity.ok(paymentService.save(paymentSaveDto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
