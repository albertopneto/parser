package com.assessment.parser.service;

import com.assessment.parser.entity.Event;

public interface EventService {

    /**
     * Method responsible to save the Event.
     *
     * @param event The Event to be stored
     */
    void saveEvent(Event event);

}
