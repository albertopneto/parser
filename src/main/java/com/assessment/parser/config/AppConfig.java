package com.assessment.parser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "com.assessment.parser" })
@EnableJpaRepositories(basePackages = { "com.assessment.parser.repository" })
public class AppConfig {

}