package com.ekizmaz.payment.dto;

import com.ekizmaz.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSaveDto {
    private Long userId;
    private Long schedule_id;
    private PaymentType paymentType;
    List<PassegerDto> passengerList;
}
