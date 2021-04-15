package com.assessment.parser.repository;

import com.assessment.parser.entity.Event;
import com.assessment.parser.enumeration.LogType;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {

    @Mock
    private EventRepository eventRepository;

    @Test
    public void saveEventLogSuccessfully_whenRequiredFieldsAreSetOnly() {
        // given
        Event event = new Event();
        event.setEventId("scsmbstgrb");
        event.setEventDuration(2);
        event.setAlert(false);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // when
        Event storedEvent = eventRepository.save(event);

        // then
        assertNotNull(storedEvent);
        assertEquals("scsmbstgrb", storedEvent.getEventId());
        assertEquals(2, storedEvent.getEventDuration());
        assertFalse(storedEvent.isAlert());
        assertNull(storedEvent.getType());
        assertNull(storedEvent.getHost());
        verify(eventRepository, times(1)).save(event);
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void saveEventLogSuccessfully_whenAllFieldsAreProvided() {
        // given
        Event event = getEventLogWithAllFieldsSet();
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // when
        Event storedEvent = eventRepository.save(event);

        // then
        assertNotNull(storedEvent);
        assertEquals("scsmbstgrc", storedEvent.getEventId());
        assertEquals(8, storedEvent.getEventDuration());
        assertTrue(storedEvent.isAlert());
        assertEquals(LogType.APPLICATION_LOG, storedEvent.getType());
        assertEquals("12345", storedEvent.getHost());
        verify(eventRepository, times(1)).save(event);
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void findEventLogsSuccessfully_whenDatabaseIsEmpty() {
        // given
        when(eventRepository.findAll()).thenReturn(Lists.emptyList());

        // when
        List<Event> events = eventRepository.findAll();

        // then
        assertTrue(events.size() == 0);
        verify(eventRepository, times(1)).findAll();
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void findEventLogsSuccessfully_whenDatabaseIsNotEmpty() {
        // given
        Event event = getEventLogWithAllFieldsSet();
        when(eventRepository.findByEventId(anyString())).thenReturn(event);

        // when
        Event eventLoaded = eventRepository.findByEventId("scsmbstgrc");

        // then
        assertNotNull(eventLoaded);
        assertEquals("scsmbstgrc", eventLoaded.getEventId());
        verify(eventRepository, times(1)).findByEventId("scsmbstgrc");
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