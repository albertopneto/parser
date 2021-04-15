package com.assessment.parser;

import com.assessment.parser.exception.ServiceException;
import com.assessment.parser.service.FileProcessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    private final FileProcessService fileProcessService;

    @Override
    public void run(String... args) {
        if (args.length <= 0) {
            log.error("Please provide the full path for the logfile as argument");
        } else {
            String fullPath = args[0];
            try {
                System.out.println("\n\n\n\n"); // System.out is just to have a better space between the logs
                log.info("******************** Initializing parser for the file [{}] ********************", fullPath);
                System.out.println("\n\n\n\n");

                fileProcessService.processFile(fullPath);

                System.out.println("\n\n\n\n");
                log.info("******************** Parser has been completed successfully for the file ********************");
                System.out.println("\n\n\n\n");
            } catch (ServiceException e) {
                System.out.println("\n\n\n\n");
                log.error("******************** Error executing the parser, please check the logs ********************");
                System.out.println("\n\n\n\n");
            }
        }
    }

}