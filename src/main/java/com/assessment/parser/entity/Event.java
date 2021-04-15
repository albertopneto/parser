package com.assessment.parser.entity;

import com.assessment.parser.enumeration.LogType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    @Column(unique = true)
    private String eventId;

    @Column(nullable = false)
    private long eventDuration;

    @Enumerated(EnumType.STRING)
    private LogType type;

    private String host;

    @Column(nullable = false)
    private boolean alert;

}