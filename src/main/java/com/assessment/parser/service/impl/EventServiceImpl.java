package com.assessment.parser.service.impl;

import com.assessment.parser.entity.Event;
import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.repository.EventRepository;
import com.assessment.parser.service.EventService;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public void saveEvent(Event event) {
        Try.of(() -> eventRepository.save(event))
                .onFailure(throwable -> {
                            String errorMessage = String.format("Error storing EventId [%s], reason [%s]", event.getEventId(), throwable.getMessage());
                            log.error(errorMessage);
                            throw new ServiceException(errorMessage);
                        }
                );
    }

}