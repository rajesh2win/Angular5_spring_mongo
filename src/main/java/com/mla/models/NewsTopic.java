package com.mla.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
/**
 * Created by rnallamothu on 4/15/18.
 */
@Document(collection = "newstopics")
public class NewsTopic {
    @Id
    String id;
    String topicName;
    String topicDetails;
    String imageUrl;
    Date  createdDate ;

    public NewsTopic(String id, String topicName, String topicDetails,Date createdDate) {
        this.id = id;
        this.topicName = topicName;
        this.topicDetails = topicDetails;
        this.createdDate  = createdDate;
    }

    public NewsTopic() {

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
