package com.ekizmaz.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Boolean scheduleStatus;
}
