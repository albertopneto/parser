package com.assessment.parser.service;

public interface FileProcessService {

    /**
     * Method responsible to process the log file for a given a path.
     *
     * @param path The path to the log file.
     */
    void processFile(String path);

}
