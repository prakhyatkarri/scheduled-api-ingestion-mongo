package com.schedule.control.task.repository;

import com.mongodb.Mongo;
import com.schedule.control.task.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    List<Activity> findByType(String type);
}
