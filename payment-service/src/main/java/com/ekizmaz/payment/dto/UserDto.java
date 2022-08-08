package com.ekizmaz.payment.dto;

import com.ekizmaz.payment.enums.FirmType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id ;

    private String username;

    private String name;

    private String surname;

    private String email;

    private FirmType firmType;

}
