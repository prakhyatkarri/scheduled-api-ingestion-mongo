package com.schedule.control.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Activity {
    @Id
    private String id;
    private String activity;
    private String accessibility;
    private String type;
    private String participants;
    private String price;
    private String link;
    private String key;
}
