package com.assessment.parser.service.impl;

import com.assessment.parser.dto.EventDTO;
import com.assessment.parser.entity.Event;
import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.service.EventService;
import com.assessment.parser.service.FileProcessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

@Service
@AllArgsConstructor
@Slf4j
public class FileProcessServiceImpl implements FileProcessService {

    private final EventService eventService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void processFile(String path) {
        final long startProcessingTime = System.currentTimeMillis();
        HashMap<String, EventDTO> events = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = in.readLine()) != null) {
                EventDTO eventDTO = convertJSONToEventDTO(line);
                if (events.containsKey(eventDTO.getId())) {
                    EventDTO firstEventLog = events.get(eventDTO.getId());
                    long duration = Math.abs(firstEventLog.getTimestamp() - eventDTO.getTimestamp());

                    Event event = new Event();
                    event.setEventId(firstEventLog.getId());
                    event.setEventDuration(duration);
                    event.setHost(firstEventLog.getHost());
                    event.setType(firstEventLog.getType());
                    if (duration > 4) {
                        event.setAlert(true);
                    }
                    events.remove(eventDTO.getId());
                    eventService.saveEvent(event);
                } else {
                    events.put(eventDTO.getId(), eventDTO);
                }
            }
            log.info("File [{}] successfully parsed in [{}] miliseconds", path, (System.currentTimeMillis() - startProcessingTime));
        } catch (FileNotFoundException e) {
            String errorMessage = String.format("Provided file [%s] doesn't exists, please check the full path of the File", path);
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        } catch (Exception e) {
            String errorMessage = String.format("Error reading file [%s], reason [%s]", path, e.getMessage());
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private EventDTO convertJSONToEventDTO(String line) {
        EventDTO eventDTO;
        try {
            eventDTO = objectMapper.readValue(line, EventDTO.class);
        } catch (JsonProcessingException e) {
            String errorMessage = String.format("Error converting the JSON [%s] to EventDTO, reason [%s]", line, e.getMessage());
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        return eventDTO;
    }

}