package com.ekizmaz.payment.entity;

import com.ekizmaz.payment.enums.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "payment")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;

    @Setter
    @Column
    private Long ticketId;


    @Setter
    @Column
    private String numberOfSeat;

    @Setter
    @Column
    private Float amountPaid;

    @Setter
    @Column
    private Date paymentDate;

    @Setter
    @Column
    private Long userId;

    @Setter
    @Column
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;
}
