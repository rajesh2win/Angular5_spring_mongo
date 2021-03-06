package com.mla.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rnallamothu on 4/15/18.
 */
@Document(collection = "Work")
public class Work {
    @Id
    String id;
    String topicName;
    String topicDetails;
    String imageUrl;
    String item;

    public Work(String id, String topicName, String topicDetails,String item) {
        this.id = id;
        this.topicName = topicName;
        this.topicDetails = topicDetails;
        this.item = item;
    }

    public Work() {

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

    public String getTopicDetails() {
        return topicDetails;
    }

    public void setTopicDetails(String topicDetails) {
        this.topicDetails = topicDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


}
