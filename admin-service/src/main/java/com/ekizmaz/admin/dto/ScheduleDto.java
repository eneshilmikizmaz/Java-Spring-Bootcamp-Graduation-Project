package com.ekizmaz.admin.dto;

import com.ekizmaz.admin.enums.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Long id;
    private String startingPoint;
    private String destination;
    private Date scheduleDate;
    private Date departureTime;
    private Date arrivalTime;
    private Float fareAmount;
    private int capacity;
    private Vehicle vehicle;
    private Boolean scheduleStatus;
}
