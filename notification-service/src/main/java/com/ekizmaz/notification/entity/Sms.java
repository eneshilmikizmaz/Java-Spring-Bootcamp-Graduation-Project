package com.ekizmaz.notification.entity;

import com.ekizmaz.notification.dto.Notification;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "messages")
public class Sms {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;

    @Setter
    @Column
    private String message;

    @Setter
    @Column
    private String phoneNumber;

}
