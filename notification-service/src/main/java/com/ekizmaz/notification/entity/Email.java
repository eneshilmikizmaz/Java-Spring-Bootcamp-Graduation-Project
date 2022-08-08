package com.ekizmaz.notification.entity;

import com.ekizmaz.notification.dto.Notification;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "emails")
public class Email implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;

    @Setter
    @Column
    private String message;

    @Setter
    @Column
    private String email;

}
