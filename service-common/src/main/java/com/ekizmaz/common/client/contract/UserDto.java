package com.ekizmaz.common.client.contract;

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
    private String firmType;
    private String phoneNumber;
}
