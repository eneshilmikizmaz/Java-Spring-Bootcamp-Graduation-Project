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
public class PassegerDto {
    private String numberOfSeat;
    private Gender gender;
}
