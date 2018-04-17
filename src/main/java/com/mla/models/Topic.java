package com.mla.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rnallamothu on 4/15/18.
 */
@Document(collection = "topics")
public class Topic {
    @Id
    String id;
    String topicName;
    String topicDetails;

    public Topic(String id, String topicName, String topicDetails) {
        this.id = id;
        this.topicName = topicName;
        this.topicDetails = topicDetails;
    }

    public Topic(){

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
}
