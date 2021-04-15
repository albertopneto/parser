package com.assessment.parser.dto;

import com.assessment.parser.enumeration.LogType;
import com.assessment.parser.enumeration.State;
import lombok.Data;

@Data
public class EventDTO {

    private String id;

    private State state;

    private long timestamp;

    private LogType type;

    private String host;

}
