package com.schedule.control.task.service;

import com.schedule.control.task.model.Activity;
import com.schedule.control.task.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    @Value("${ingestion.api.url}")
    private String apiURL;

    @Override
    public List<Activity> getAllActivities() {
        log.debug("Getting all activities");
        List<Activity> list = this.activityRepository.findAll();
        log.debug("All list size:: "+list.size());
        return list;
    }

    @Override
    public Activity createActivity() {
        RestTemplate restTemplate = new RestTemplate();
        Activity activity = restTemplate.getForObject(apiURL, Activity.class);
        Activity savedActivity = this.activityRepository.save(activity);
        log.debug("Create Result:: " + savedActivity);
        return savedActivity;
    }

    @Override
    public List<Activity> updateActivities(String type) {
        List<Activity> activities = this.activityRepository.findByType(type);
        log.debug("Found {} records for type {}", activities.size(), type);

        activities.forEach(ac -> {
            if (!ac.getAccessibility().isEmpty()) {
                Double value = Double.parseDouble(ac.getAccessibility());
                ac.setAccessibility(String.valueOf(value + new Double(0.1)));
            }
        });

        List<Activity> list = this.activityRepository.saveAll(activities);
        log.debug("Updated list:: "+list);
        return list;
    }
}
