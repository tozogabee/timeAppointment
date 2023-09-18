package com.sonrisa.timeappointmentapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPOINTMENT")
@Getter
@Setter
@ToString
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "from")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime from;

    @Column(name = "to")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime to;

}
