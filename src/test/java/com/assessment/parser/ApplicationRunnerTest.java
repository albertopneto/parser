package com.assessment.parser;

import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.service.FileProcessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ApplicationRunnerTest {

    @InjectMocks
    ApplicationRunner applicationRunner;

    @Mock
    private FileProcessService fileProcessService;

    @Test
    void runnerShouldCompleteWithoutProcessingFile_whenNoArgsProvided() {
        applicationRunner.run();
    }

    @Test
    void runnerShouldCompleteSuccessfully_whenValidArgsProvided() {
        doNothing().when(fileProcessService).processFile(anyString());

        applicationRunner.run("logfile.txt");

        verify(fileProcessService, times(1)).processFile(anyString());
        verifyNoMoreInteractions(fileProcessService);
    }

    @Test
    void runnerShouldLogError_whenLogFileHasInvalidContent() {
        doThrow(new ServiceException("Error processing file")).when(fileProcessService).processFile(anyString());

        applicationRunner.run("logfile.txt");

        verify(fileProcessService, times(1)).processFile(anyString());
        verifyNoMoreInteractions(fileProcessService);
    }

}
