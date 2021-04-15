package com.assessment.parser.service;

import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.service.impl.FileProcessServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FileProcessServiceTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private FileProcessServiceImpl fileProcessService;

    @Test
    public void saveEventLogSuccessfully_whenRequiredFieldsAreSetOnly() {
        // given
        doNothing().when(eventService).saveEvent(any());

        // when
        fileProcessService.processFile("logfile.txt");

        // then
        verify(eventService, times(3)).saveEvent(any());
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void saveEventLogFails_whenFileNotFound() {
        Assertions.assertThrows(ServiceException.class, () -> fileProcessService.processFile("logfileNotExists.txt"));

        verify(eventService, times(0)).saveEvent(any());
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void saveEventLogFails_whenJSONIsInvalid() {
        Assertions.assertThrows(ServiceException.class, () -> fileProcessService.processFile("invalid_logfile.txt"));

        verify(eventService, times(0)).saveEvent(any());
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void saveEventLogFail_whenIOExceptionIsThrown() {
        doThrow(new RuntimeException("Unexpected Error")).when(eventService).saveEvent(any());

        Assertions.assertThrows(ServiceException.class, () -> fileProcessService.processFile("logfile.txt"));
    }

}