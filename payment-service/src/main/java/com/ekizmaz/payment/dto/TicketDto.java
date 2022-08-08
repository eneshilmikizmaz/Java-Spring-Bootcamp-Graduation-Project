package com.ekizmaz.payment.dto;

import com.ekizmaz.payment.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    public Long id;
    public String plan;
    public String userName;
    public String numberOfSeat;
    public Double fareAmount;
    private Gender gender;
    public String dateOfBooking;
}
