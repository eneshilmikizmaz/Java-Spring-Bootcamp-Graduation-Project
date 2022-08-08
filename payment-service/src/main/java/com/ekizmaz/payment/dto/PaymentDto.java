package com.ekizmaz.payment.dto;

import com.ekizmaz.payment.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private Long ticketId;
    private String amountPaid;
    private Date paymentDate;
    private Long userId;
    private PaymentType paymentType;
}
