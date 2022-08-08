package com.ekizmaz.ticket.entity;

import com.ekizmaz.ticket.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "tickets")
public class Ticket implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;

    @Setter
    @Column
    private Long schedule_id;

    @Setter
    @Column
    private Long user_id;

    @Setter
    @Column
    private String numberOfSeat;

    @Setter
    @Column
    private Date dateOfBooking;

    @Setter
    @Column
    private Double fareAmount;

    @Setter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Setter
    @Column
    private Boolean booking_status;

    @Column(name = "created_at")
    private Date createdAt;

}
