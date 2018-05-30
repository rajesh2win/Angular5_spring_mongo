package com.mla.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rnallamothu on 4/15/18.
 */
@Document(collection = "WorkCategory")
public class WorkCategory {
    @Id
    String id;
    String topicName;
    String imageUrl;

    public WorkCategory(String id, String topicName) {
        this.id = id;
        this.topicName = topicName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
