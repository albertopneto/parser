package com.assessment.parser.service;

import com.assessment.parser.entity.Event;
import com.assessment.parser.enumeration.LogType;
import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.repository.EventRepository;
import com.assessment.parser.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    public void saveEventLogSuccessfully_whenRequiredFieldsAreSetOnly() {
        // given
        Event event = new Event();
        event.setEventId("scsmbstgrb");
        event.setEventDuration(2);
        event.setAlert(false);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // when
        eventService.saveEvent(event);

        // then
        verify(eventRepository, times(1)).save(event);
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void saveEventLogFails_whenEventIdIsDuplicated() {
        Event event = new Event();
        event.setEventId("scsmbstgrb");
        event.setEventDuration(2);
        event.setAlert(false);
        when(eventRepository.save(any(Event.class))).thenThrow(new RuntimeException("Event already exists."));

        Assertions.assertThrows(ServiceException.class, () -> eventService.saveEvent(event));
    }

    @Test
    public void saveEventLogSuccessfully_whenAllFieldsAreProvided() {
        // given
        Event event = getEventLogWithAllFieldsSet();
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // when
        eventService.saveEvent(event);

        // then
        verify(eventRepository, times(1)).save(event);
        verifyNoMoreInteractions(eventRepository);
    }

    private Event getEventLogWithAllFieldsSet() {
        Event event = new Event();
        event.setEventId("scsmbstgrc");
        event.setEventDuration(8);
        event.setAlert(true);
        event.setType(LogType.APPLICATION_LOG);
        event.setHost("12345");
        return event;
    }

}