package com.ekizmaz.ticket.dto;

import com.ekizmaz.ticket.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Double fareAmount;
    private Gender gender;
}
