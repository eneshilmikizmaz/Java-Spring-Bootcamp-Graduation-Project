package com.ekizmaz.admin.entity;

import com.ekizmaz.admin.enums.Vehicle;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "schedule")
public class Schedule implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;

    @Setter
    @Column
    private String startingPoint;

    @Setter
    @Column
    private String destination;

    @Setter
    @Column
    private Date scheduleDate;

    @Setter
    @Column
    private Date departureTime;

    @Setter
    @Column
    private Date arrivalTime;

    @Setter
    @Column
    private Float fareAmount;

    @Setter
    @Column
    private int capacity;

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @Setter
    @Column
    private Boolean scheduleStatus;

    @Column(name = "created_at")
    private Date createdAt;

}
