package com.ekizmaz.payment.dto;

import com.ekizmaz.payment.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketSaveDto {
    private Long schedule_id;
    private Long user_id;
    private String numberOfSeat;
    private Date dateOfBooking;
    private Float fareAmount;
    private Gender gender;
}
