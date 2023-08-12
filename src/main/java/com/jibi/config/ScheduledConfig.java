package com.jibi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/** The type Scheduled config. */
@Configuration
@EnableScheduling
@ComponentScan("com.jibi.schedule")
public class ScheduledConfig {}
