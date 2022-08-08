package com.ekizmaz.admin.dto;

import com.ekizmaz.admin.enums.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSaveDto {
    private String startingPoint;
    private String destination;
    private Date scheduleDate;
    private Date departureTime;
    private Date arrivalTime;
    private Float fareAmount;
    private Vehicle vehicle;
}
