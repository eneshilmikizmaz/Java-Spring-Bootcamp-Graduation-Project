package com.ekizmaz.user.dto;

import com.ekizmaz.user.enums.AppUserRole;
import com.ekizmaz.user.enums.FirmType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Data
@Getter
@Setter
public class UserSaveDto  {
    private String username;
    private String name;
    private String surname;
    private String email;
    private FirmType firmType;
    private String password;
}
