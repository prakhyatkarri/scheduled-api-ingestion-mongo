package com.schedule.control.task.scheduled;

import com.schedule.control.task.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class TaskController {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private int count = 0;

    @Autowired
    private ActivityService activityService;


    @Scheduled(fixedRateString = "${task.ingest.schedule.delay.rate}")
    public void ingestFromApi() {
        log.debug("ingestFromApi started at " + ts());

        try {
            log.debug("Creating new Activity");
            this.activityService.createActivity();

            log.debug("Getting all activities after creating new activity");
            this.activityService.getAllActivities();

            log.debug("Updating activities by type");
            this.activityService.updateActivities("social");

            log.debug("Getting all activities after updating by type");
            this.activityService.getAllActivities();
        } catch (Exception e) {
            log.error("Exception in ingestFromApi::",e);
        }
        log.debug("ingestFromApi completed at " + ts());
    }

    private String ts() {
        return this.formatter.format(new Date());
    }
}
