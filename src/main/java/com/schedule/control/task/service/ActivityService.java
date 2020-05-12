package com.schedule.control.task.service;

import com.schedule.control.task.model.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivities();

    Activity createActivity();

    List<Activity> updateActivities(String type);
}
